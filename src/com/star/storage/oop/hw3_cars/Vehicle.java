package com.star.storage.oop.hw3_cars;

public abstract class Vehicle {
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
}
