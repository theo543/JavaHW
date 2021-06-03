package com.star.storage.oop.hw3.cars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.max;

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
        var movements = car.getMovements();
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
        for (var m : movements) {
            random.setSeed((long) (m.x + m.y + m.angle + m.arcAngle + 10 + (m.isArc ? 1 : 0) + (m.right ? 1 : 0)));
            g.setColor(new Color(abs(random.nextInt() % 255), abs(random.nextInt() % 255), abs(random.nextInt() % 255)));
            if (!m.isArc) {
                g.draw(new Line2D.Double(x, y, m.x, m.y));
            } else {
                //todo
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
