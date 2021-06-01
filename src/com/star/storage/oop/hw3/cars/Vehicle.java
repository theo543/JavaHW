package com.star.storage.oop.hw3.cars;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.*;

public abstract class Vehicle {
    protected final ArrayList<Movement> positions = new ArrayList<>();
    protected double speed;//mps
    protected double angle;//degrees
    protected double x, y, xStart, yStart;//meters

    public double getSpeed() {
        return speed;
    }

    public BufferedImage pathToImage() {
        double minX = -100, maxX = 100, minY = -100, maxY = 100;
        for (var m : positions) {
            minX = min(minX, m.x);
            maxX = max(maxX, m.x);
            minY = min(minY, m.y);
            maxY = max(maxY, m.y);
        }
        BufferedImage image = new BufferedImage(max((int) (maxX - minX), 300), max((int) (maxY - minY), 300), BufferedImage.TYPE_INT_ARGB);
        double x = this.x, y = this.y;
        var g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.BLUE);
        for (var m : positions) {
            g.drawLine((int) (x - minX), (int) (y - minY), (int) (m.x - minX), (int) (m.y * minY));
            x = m.x;
            y = m.y;
        }
        return image;
    }

    public double getAngle() {
        return angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //assumes constant velocity and angle
    //public move method should call this
    //acceleration methods should call this with average velocity and time taken to reach target velocity
    protected void move(double seconds, double speed, double angle, boolean recordMove) {
        double r_angle = toRadians(angle);
        x += speed * seconds * cos(r_angle);
        y += speed * seconds * sin(r_angle);
        if (recordMove)
            positions.add(new Movement(x, y));
    }

    protected void move(double seconds, double speed, double angle) {
        move(seconds, speed, angle, true);
    }

    public List<Movement> getMovements() {
        return Collections.unmodifiableList(positions);
    }

    static class Movement {
        double x;
        double y;
        boolean isArc = false;
        double angle;
        boolean right;

        public Movement(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Movement(double x, double y, double angle, boolean right) {
            this.x = x;
            this.y = y;
            isArc = true;
            this.angle = angle;
            this.right = right;
        }
    }
}
