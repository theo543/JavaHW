package com.star.storage.oop.hw3.cars;

import static java.lang.Math.*;

public class Car extends Vehicle {

    //initial angle and position
    //angle of 0 degrees is east
    public Car(double angle, double x, double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        xStart = x;
        yStart = y;
    }

    public double getAcceleration() {
        return 3.5;//mps^-2
    }

    public double getDeceleration() {
        return 7;//mps^-2
    }

    public double getSteering() {
        return 40;//degrees per second
    }

    public void move(double seconds) {
        move(seconds, speed, angle);
    }

    //returns current speed
    public double changeSpeed(double targetSpeed) {
        targetSpeed = min(getMaxSpeed(), targetSpeed);
        targetSpeed = max(targetSpeed, 0);
        double dv = (targetSpeed > speed) ? getAcceleration() : getDeceleration();
        move(abs(targetSpeed - speed) / dv, (targetSpeed + speed) / 2, angle);
        speed = targetSpeed;
        return speed;
    }

    //returns current angle
    public double steer(double targetAngle, boolean right) {
        if (speed == 0)
            return angle;
        targetAngle = (targetAngle + 360) % 360;
        double tick = 0.01;
        double angleChange = getSteering() * tick * (right ? 1 : -1);
        while (abs(targetAngle - angle) > abs(angleChange * 1.5)) {
            move(tick, speed, angle, false);
            angle += angleChange;
            angle = (angle + 360) % 360;
        }
        positions.add(new Movement(x, y, angleChange, right));
        angle = targetAngle;
        return angle;
    }

    public double getMaxSpeed() {
        return 44.4444444444444;//mps, ~= 160 km/s
    }
}
