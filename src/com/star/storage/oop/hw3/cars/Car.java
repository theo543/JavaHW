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

    protected void move(double seconds, double speed, double angle) {
        x += speed * seconds * cos(angle);
        y += speed * seconds * sin(angle);
        addMovement(new Movement(x, y, this.angle));
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
        double dir = right ? 1 : -1;
        var r = speed / getSteering();
        double arcAngle = change % (2 * PI);
        double dist = 2 * r * sin(arcAngle / 2);
        var a = (angle + arcAngle / 2 * dir + 2 * PI) % (2 * PI);
        x += dist * cos(a);
        y += dist * sin(a);
        angle += (arcAngle * dir + 2 * PI) % (2 * PI);
        addMovement(new Movement(x, y, angle, change, r, right));
    }

    public double getMaxSpeed() {
        return 44.4444444444444;//mps, ~= 160 km/s
    }

    static class Movement {
        double x;
        double y;
        double angle;
        SteeringData sData = null;

        public Movement(double x, double y, double angle, double angleChange, double radius, boolean right) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            sData = new SteeringData(angleChange, radius, right);
        }

        public Movement(double x, double y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        record SteeringData(double angleChange, double radius, boolean right) {
        }
    }
}
