package com.star.storage.oop.hw1;

import static com.star.storage.oop.Main.Assert;

public class Point{
	private static Point origin = new Point();
	private int x, y;

	public Point(){
		x = 0;
		y = 0;
	}

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public double distance(Point p){
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
	}

	public double distance(){
		return distance(origin);
	}

	public static void testPoint(){
		Point first = new Point(6, 5);
		Point second = new Point(3, 1);
		Assert(first.distance() >= 7.810249);
		Assert(first.distance() <= 7.81025);
		Assert(first.distance(second) >= 5.0);
		Assert(first.distance(second) <= 5.01);
		Point point = new Point();
		Assert(point.distance() == 0.0);
		// distance(0,0)= 7.810249675906654
		// distance(second)= 5.0
		// distance()= 0.0
	}
}
