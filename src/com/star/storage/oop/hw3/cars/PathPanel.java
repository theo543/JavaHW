package com.star.storage.oop.hw3.cars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import static java.lang.Math.*;

public class PathPanel extends JPanel {
    private final Car car;

    public PathPanel(Car car) {
        this.car = car;
        setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (car) {
                    try {
                        car.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                validate();
                repaint();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        var prevTransform = g.getTransform();
        double squareLen = 100;
        for (var m : car.getMovements()) {
            squareLen = max(max(abs(m.x), abs(m.y)), squareLen);
        }
        double x = car.getXStart(), y = car.getYStart();
        g.setStroke(new BasicStroke((float) (squareLen / 50), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.scale(getSize().width / (2 * squareLen), getSize().height / (2 * squareLen));
        g.translate(squareLen, squareLen);
        g.setColor(Color.DARK_GRAY);
        g.draw(new Line2D.Double(0, -squareLen, 0, squareLen));
        g.draw(new Line2D.Double(squareLen, 0, -squareLen, 0));
        Random random = new Random(car.hashCode());
        for (var m : car.getMovements()) {
            g.setColor(new Color(abs(random.nextInt() % 255), abs(random.nextInt() % 255), abs(random.nextInt() % 255)));
            if (!m.isArc) {
                g.draw(new Line2D.Double(x, y, m.x, m.y));
            } else {
                Point2D.Double to = new Point2D.Double(m.x, m.y);
                Point2D.Double middle = new Point2D.Double((x + to.x) / 2, (y + to.y) / 2);
                Point2D.Double c;
                Arc2D arc = new Arc2D.Double();
                double chord = to.distance(x, y), chordAngle, height, radius, hA, arcStart, arcExtent;
                double arcAngle = m.arcAngle % (2 * PI);
                if (arcAngle < PI) {
                    chordAngle = arcAngle;
                    radius = chord * sin((PI - chordAngle) / 2) / sin(chordAngle);
                    height = sqrt(pow(radius, 2) - pow(middle.distance(to), 2));
                    hA = atan2(middle.y - y, middle.x - x) + PI / 2;
                    c = new Point2D.Double(middle.x + cos(hA) * height * (m.right ? 1 : -1), middle.y + sin(hA) * height * (m.right ? 1 : -1));
                } else if (arcAngle > PI) {
                    chordAngle = arcAngle - PI;
                    radius = chord * sin((2 * PI - chordAngle) / 2) / sin(chordAngle);
                    height = sqrt(pow(radius, 2) - pow(middle.distance(to), 2));
                    hA = atan2(middle.y - y, middle.x - x) - PI / 2;
                    c = new Point2D.Double(middle.x + cos(hA) * height * (m.right ? 1 : -1), middle.y + sin(hA) * height * (m.right ? 1 : -1));
                } else {
                    c = middle;
                    radius = chord / 2;
                }
                if (m.arcAngle >= 2 * PI) {
                    g.draw(new Ellipse2D.Double(c.x - radius, c.y - radius, 2 * radius, 2 * radius));
                } else {
                    arcStart = toDegrees(atan2(c.y - y, x - c.x));
                    arcExtent = toDegrees(arcAngle) * (m.right ? -1 : 1);
                    arc.setArcByCenter(c.x, c.y, radius, arcStart, arcExtent, Arc2D.OPEN); //for some reason these angles are anticlockwise?
                    g.draw(arc);
                }
            }
            x = m.x;
            y = m.y;
            var prev = g.getTransform();
            //draw arrow
            g.rotate(m.angle, x, y);
            g.draw(new Line2D.Double(x - squareLen / 50, y + squareLen / 50, x, y));
            g.draw(new Line2D.Double(x, y, x - squareLen / 50, y - squareLen / 50));
            g.setTransform(prev);
        }
        g.setTransform(prevTransform);
    }
}
