/*package com.star.storage.oop;

import com.star.storage.oop.hw1.ComplexNumber;
import com.star.storage.oop.hw1.Person;
import com.star.storage.oop.hw1.Point;
import com.star.storage.oop.hw1.Wall;
import com.star.storage.oop.hw3.shapes.Cuboid;
import com.star.storage.oop.hw3.shapes.Rectangle;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestManager{
	public static void init(){
		registerTest("all", () -> tests.forEach((a, b) -> {
			if(!a.equals("all")) run(Collections.singletonList(a));
		}));
		registerTest("person", Person::testPerson);
		registerTest("complex-number", ComplexNumber::testComplexNumber);
		registerTest("point", Point::testPoint);
		registerTest("wall", Wall::testWall);
		registerTest("cuboid", Cuboid::testCuboid);
		registerTest("rectangle", Rectangle::testRectangle);
		Main.registerCommand("test", TestManager::run);
		Main.registerCommand("toggle-assertion-error", TestManager::toggleAssertionError);
	}
}
*/