package com.star.storage.oop.hw3.cars;

import java.util.ArrayList;

import static java.lang.Math.*;

public abstract class Vehicle {
    protected final ArrayList<Point> positions = new ArrayList<>();
    protected double speed;//mps
    protected double angle;//degrees
    protected double x, y;//meters

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
    protected void move(double seconds, double speed, double angle) {
        double r_angle = toRadians(angle);
        x += speed * seconds * cos(r_angle);
        y += speed * seconds * sin(r_angle);
        positions.add(new Point(x, y));
    }

    protected record Point(double x, double y) {
    }
}
