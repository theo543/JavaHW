package com.star.storage.oop.hw3.cars;

import static java.lang.Math.*;

public class Car extends Vehicle{

	public double getAcceleration(){
		return 3.5;//mps^-2
	}

	public double getDeceleration(){
		return 7;//mps^-2
	}

	public double getSteering(){
		return 40;//degrees per second
	}

	public void move(double seconds){
		move(seconds, speed, angle);
	}

	//returns current speed
	public double changeSpeed(double targetSpeed){
		targetSpeed = min(getMaxSpeed(), targetSpeed);
		targetSpeed = max(targetSpeed, 0);
		double dv = (targetSpeed > speed) ? getAcceleration() : getDeceleration();
		move(abs(targetSpeed - speed)/dv, (targetSpeed + speed)/2, angle);
		speed = targetSpeed;
		return speed;
	}

	//returns current angle
	public double steer(double targetAngle, boolean right){
		if(speed == 0)
			return angle;
		targetAngle %= 360;
		if(targetAngle < 0)
			targetAngle += 360;
		double tick = 0.00001;//100000 simulations per second
		double angleChange = getSteering() * tick * (right ? 1 : -1);
		while(abs(targetAngle - angle) > angleChange){
			move(tick, speed, angle);
			angle += angleChange;
		}
		angle = targetAngle;
		return angle;
	}

	public double getMaxSpeed(){
		return 44.4444444444444;//mps, ~= 160 km/s
	}

	//initial angle and position
	//angle of 0 degrees is east
	public Car(double angle, double x, double y){
		this.angle = angle;
		this.x = x;
		this.y = y;
	}
}
