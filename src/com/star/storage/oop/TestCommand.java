package com.star.storage.oop;

import com.star.storage.oop.hw1.ComplexNumber;
import com.star.storage.oop.hw1.Person;
import com.star.storage.oop.hw1.Point;
import com.star.storage.oop.hw1.Wall;
import com.star.storage.oop.hw3.shapes.Cuboid;
import com.star.storage.oop.hw3.shapes.Rectangle;

import java.util.function.Consumer;

public class TestCommand{
	private final CommandParser parser = new CommandParser();

	public TestCommand(){
		parser.add("toggle-assertion-error", (a) -> {
			if(a.matches("(?:true|false) ?"))
				AssertProvider.setThrowsError(a.startsWith("t"));
			else AssertProvider.setThrowsError(!AssertProvider.getThrowsError());
			System.out.println("Assert errors are now " + (AssertProvider.getThrowsError() ? "on" : "off"));
		});
		parser.add("all", (a) -> {
			for(var t : parser.getCommands()){
				if(t.command() instanceof TestWrapper)//avoid infinite loop
					t.command().accept("");
			}
		});
		addTest("person", Person::testPerson);
		addTest("complex-number", ComplexNumber::testComplexNumber);
		addTest("point", Point::testPoint);
		addTest("wall", Wall::testWall);
		addTest("cuboid", Cuboid::testCuboid);
		addTest("rectangle", Rectangle::testRectangle);
	}

	private static class TestWrapper implements Consumer<String>{
		Runnable r;
		String name;
		public TestWrapper(Runnable r, String name){
			this.r = r;
			this.name = name;
		}
		public void accept(String args){
			r.run();
			System.out.println("\"" + name + "\" test done");
		}
	}

	public void addTest(String name, Runnable r){
		parser.add(name, new TestWrapper(r, name));
	}

	public Consumer<String> getCommand(){
		return parser::parse;
	}
}
