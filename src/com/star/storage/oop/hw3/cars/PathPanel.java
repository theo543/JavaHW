package com.star.storage.oop.hw3.cars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

import static java.lang.Math.*;

public class PathPanel extends JPanel {
    private final Vehicle v;

    public PathPanel(Vehicle v) {
        this.v = v;
        setForeground(Color.CYAN);
        setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (v) {
                    try {
                        v.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    validate();
                    repaint();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    protected void paintComponent(Graphics graphics) {
        //System.out.println("debug");
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        var prev = g.getTransform();
        var movements = v.getMovements();
        double l = 100;
        for (var m : v.getMovements()) {
            l = max(max(abs(m.x), abs(m.y)), l);
        }
        double x = v.getXStart(), y = v.getYStart();
        g.setStroke(new BasicStroke((float) (l / 50), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.scale(getSize().width / (2 * l), getSize().height / (2 * l));
        g.translate(l, l);
        g.setColor(Color.DARK_GRAY);
        g.draw(new Line2D.Double(0, -l, 0, l));
        g.draw(new Line2D.Double(l, 0, -l, 0));
        Random r = new Random(v.hashCode());
        for (var m : movements) {
            r.setSeed((long) (m.x + m.y + m.angle + m.arcAngle + 10 + (m.isArc ? 1 : 0) + (m.right ? 1 : 0)));
            g.setColor(new Color(abs(r.nextInt() % 255), abs(r.nextInt() % 255), abs(r.nextInt() % 255)));
            g.draw(new Line2D.Double(x, y, m.x, m.y));///todo implement arcs
            x = m.x;
            y = m.y;
            var tmp = g.getTransform();
            g.rotate(toRadians(m.angle), x, y);
            g.draw(new Line2D.Double(x - l / 50, y + l / 50, x, y));
            g.draw(new Line2D.Double(x, y, x - l / 50, y - l / 50));
            g.setTransform(tmp);
        }
        g.setTransform(prev);
    }
}
