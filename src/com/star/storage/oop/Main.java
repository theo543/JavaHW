package com.star.storage.oop;

import com.star.storage.oop.hw1.ComplexNumber;
import com.star.storage.oop.hw1.Person;
import com.star.storage.oop.hw1.Point;
import com.star.storage.oop.hw1.Wall;
import com.star.storage.oop.hw3.shapes.Cuboid;
import com.star.storage.oop.hw3.shapes.Rectangle;


public class Main{

	private final static boolean throwException = false; //enable to find where assert fails
	public static void Assert(boolean b){
		if(!b){
			if(throwException){
				throw(new AssertionError("Assert failed"));
			}else{
				System.out.println("Assert failed");
			}
		}
	}

	public static void main(String[] args){
		Person.testPerson();
		Wall.testWall();
		Point.testPoint();
		ComplexNumber.testComplexNumber();
		Rectangle.testRectangle();
		Cuboid.testCuboid();
	}
}
