package com.star.storage.oop.hw3_cars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;

public class PathPanel extends JPanel {
    private final Car car;
    private final ArrayList<Shape> shapes = new ArrayList<>();

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
        var movements = car.getMovements();
        for (var m : movements) {
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
        for (int i = 0; i < movements.size(); i++) {
            var m = movements.get(i);
            g.setColor(new Color(abs(random.nextInt() % 255), abs(random.nextInt() % 255), abs(random.nextInt() % 255)));
            if (shapes.size() <= i) {
                if (m.sData == null) {
                    shapes.add(new Line2D.Double(x, y, m.x, m.y));
                } else {
                    Arc2D arc = new Arc2D.Double();
                    double dir = m.sData.right() ? 1 : -1;
                    double arcAngle = m.sData.angleChange() % (2 * PI);
                    double aToC = m.angle + (PI / 2 - arcAngle) * dir;
                    double radius = m.sData.radius();
                    double cX = x + cos(aToC) * radius;
                    double cY = y + sin(aToC) * radius;
                    if (m.sData.angleChange() >= 2 * PI) {
                        shapes.add(new Ellipse2D.Double(cX - radius, cY - radius, 2 * radius, 2 * radius));
                    } else {
                        arc.setArcByCenter(cX, cY, radius, toDegrees(atan2(cY - y, x - cX)), toDegrees(arcAngle) * -dir, Arc2D.OPEN); //for some reason these angles are anticlockwise?
                        shapes.add(arc);
                    }
                }
            }
            g.draw(shapes.get(i));
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
