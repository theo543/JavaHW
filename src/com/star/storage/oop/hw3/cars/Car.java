package com.star.storage.oop.hw3.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.*;

public class Car extends Vehicle {
    private final ArrayList<Movement> movements = new ArrayList<>();
    private final double xStart;
    private final double yStart;

    //initial angle and position
    //angle of 0 degrees is east
    public Car(double angle, double x, double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        xStart = x;
        yStart = y;
    }

    protected void move(double seconds, double speed, double angle, boolean recordMove) {
        x += speed * seconds * cos(angle);
        y += speed * seconds * sin(angle);
        if (recordMove)
            addMovement(new Movement(x, y, this.angle));
    }

    protected void move(double seconds, double speed, double angle) {
        move(seconds, speed, angle, true);
    }

    public List<Movement> getMovements() {
        return Collections.unmodifiableList(movements);
    }

    public double getXStart() {
        return xStart;
    }

    public double getYStart() {
        return yStart;
    }

    protected void addMovement(Car.Movement m) {
        synchronized (this) {
            movements.add(m);
            notifyAll();
        }
    }

    public double getAcceleration() {
        return 3.5;//mps^-2
    }

    public double getDeceleration() {
        return 7;//mps^-2
    }

    public double getSteering() {
        return toRadians(40);//radians per second
    }

    public void move(double seconds) {
        move(seconds, speed, angle);
    }

    public void changeSpeed(double targetSpeed) {
        targetSpeed = min(getMaxSpeed(), targetSpeed);
        targetSpeed = max(targetSpeed, 0);
        double dv = (targetSpeed > speed) ? getAcceleration() : getDeceleration();
        move(abs(targetSpeed - speed) / dv, (targetSpeed + speed) / 2, angle);
        speed = targetSpeed;
    }

    public void steer(double change, boolean right) {
        double tick = 0.00001, changePerTick = getSteering() * tick;
        for (double x = change; x > 0; x -= changePerTick) {
            move(tick, speed, angle, false);
            angle = (angle + 2 * PI + changePerTick * (right ? 1 : -1)) % (2 * PI);
        }
        addMovement(new Movement(x, y, angle, change, right));
    }

    public double getMaxSpeed() {
        return 44.4444444444444;//mps, ~= 160 km/s
    }

    static class Movement {
        double x;
        double y;
        boolean isArc = false;
        double angle;
        double arcAngle;
        boolean right;

        public Movement(double x, double y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        public Movement(double x, double y, double angle, double arcAngle, boolean right) {
            this.x = x;
            this.y = y;
            isArc = true;
            this.angle = angle;
            this.arcAngle = arcAngle;
            this.right = right;
        }
    }
}
