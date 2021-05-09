package com.star.storage.oop;

import com.star.storage.oop.hw1.ComplexNumber;
import com.star.storage.oop.hw1.Person;
import com.star.storage.oop.hw1.Point;
import com.star.storage.oop.hw1.Wall;
import com.star.storage.oop.hw3.shapes.Cuboid;
import com.star.storage.oop.hw3.shapes.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class TestCommand{
	private CommandParser parser = new CommandParser();
	public void addTest(String name, Runnable r){
		parser.add(new CommandParser.Command(name, (a)->r.run()));
	}
	public TestCommand(){
		parser.add(new CommandParser.Command("toggle-assertion-error", (a)->{
			if(a.matches("(?:true|false) ?"))
				AssertProvider.setThrowsError(a.startsWith("t"));
			else AssertProvider.setThrowsError(!AssertProvider.getThrowsError());
			System.out.println("Assert errors are now " + (AssertProvider.getThrowsError() ? "on" : "off"));
		}));
		addTest("all", ()->parser.getCommands().forEach((c)->c.command().accept("")));
		addTest("person", Person::testPerson);
		addTest("complex-number", ComplexNumber::testComplexNumber);
		addTest("point", Point::testPoint);
		addTest("wall", Wall::testWall);
		addTest("cuboid", Cuboid::testCuboid);
		addTest("rectangle", Rectangle::testRectangle);
	}
	public Consumer<String> getCommand(){
		return parser::parse;
	}
}
