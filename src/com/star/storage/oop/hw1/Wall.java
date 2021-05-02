package com.star.storage.oop.hw1;

import static com.star.storage.oop.Main.Assert;

public class Wall{
	private double width, height;

	public double getWidth(){
		return width;
	}

	public void setWidth(double width){
		this.width = Math.max(0, width);
	}

	public double getHeight(){
		return height;
	}

	public void setHeight(double height){
		this.height = Math.max(0, height);
	}

	public double getArea(){
		return width * height;
	}

	public Wall(){
		width = 0;
		height = 0;
	}

	public Wall(double width, double height){
		this.width = width;
		this.height = height;
	}

	public static void testWall(){
		Wall wall = new Wall(5, 4);
		Assert(wall.getArea() == 20.0);
		wall.setHeight(-1.5);
		Assert(wall.getWidth() == 5.0);
		Assert(wall.getHeight() == 0.0);
		Assert(wall.getArea() == 0.0);
	}
}
