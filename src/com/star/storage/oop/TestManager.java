package com.star.storage.oop;

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
	private final static HashMap<String, Test> tests = new HashMap<>();
	private static boolean throwError = false;
	private static String currentTest;

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

	private static void toggleAssertionError(List<String> args){
		throwError = !throwError;
		if(args.size() != 0){
			if(args.get(0).equalsIgnoreCase("true"))
				throwError = true;
			else if(args.get(0).equalsIgnoreCase("false"))
				throwError = false;
		}
		System.out.println("Assertion Errors are now " + (throwError ? "on" : "off"));
	}

	private static void run(List<String> args){
		if(args.isEmpty()){
			System.out.println("Available tests:");
			tests.forEach((a, b) -> System.out.println(a));
			System.out.println("Select \"all\" to run all tests");
		}else if(tests.containsKey(args.get(0))){
			currentTest = args.get(0);
			tests.get(args.get(0)).run();
			System.out.println("Done");
			currentTest = null;
		}else{
			System.out.println("Test not found");
		}
	}

	public static void Assert(boolean b){
		if(b)
			return;
		String message = "Assert failed" + ((currentTest != null) ? (" in test " + currentTest) : "");
		if(throwError)
			throw new AssertionError(message);
		else{
			System.out.println(message);
		}
	}

	public static void registerTest(String name, Test t){
		tests.put(name, t);
	}

	@FunctionalInterface
	public interface Test{
		void run();
	}

}
