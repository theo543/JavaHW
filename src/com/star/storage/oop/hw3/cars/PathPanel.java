package com.star.storage.oop.hw3.cars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

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
            l = (int) max(max(abs(m.x), abs(m.y)), l);
        }
        int x = (int) v.getXStart(), y = (int) v.getYStart();
        g.scale(getSize().width / (2 * l), getSize().height / (2 * l));
        g.translate(l, l);
        g.setColor(Color.RED);
        g.draw(new Line2D.Double(0, -l, 0, l));
        g.draw(new Line2D.Double(l, 0, -l, 0));
        g.setColor(Color.BLUE);
        for (var m : movements) {
            g.draw(new Line2D.Double(x, y, (int) m.x, (int) m.y));///todo implement arcs
            x = (int) m.x;
            y = (int) m.y;
        }
        g.setTransform(prev);
    }
}
