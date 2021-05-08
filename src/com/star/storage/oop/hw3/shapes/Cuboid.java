package com.star.storage.oop.hw3.shapes;

import static com.star.storage.oop.TestManager.Assert;
import static java.lang.Math.max;

public class Cuboid extends Rectangle{
	private final double height;

	public Cuboid(double width, double length, double height){
		super(width, length);
		this.height = max(0, height);
	}

	public double getHeight(){
		return height;
	}

	public double getVolume(){
		return getArea() * height;
	}

	public static void testCuboid(){
		Cuboid cuboid = new Cuboid(5.3,10,5);
		Assert(cuboid.getWidth() == 5.3);
		Assert(cuboid.getLength() == 10);
		Assert(cuboid.getHeight() == 5);
		Assert(cuboid.getArea() == 53);
		Assert(cuboid.getVolume() == 265);
	}
}
