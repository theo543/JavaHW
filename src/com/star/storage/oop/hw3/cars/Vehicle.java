package com.star.storage.oop.hw3.cars;

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
