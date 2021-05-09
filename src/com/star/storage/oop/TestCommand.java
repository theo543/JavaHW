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
		parser.add(new CommandParser.Command("toggle-assertion-error", (a) -> {
			if(a.matches("(?:true|false) ?"))
				AssertProvider.setThrowsError(a.startsWith("t"));
			else AssertProvider.setThrowsError(!AssertProvider.getThrowsError());
			System.out.println("Assert errors are now " + (AssertProvider.getThrowsError() ? "on" : "off"));
		}));
		parser.add(new CommandParser.Command("all", (a) -> {
			for(var t : parser.getCommands()){
				if(t.command() instanceof TestWrapper)//avoid infinite loop
					t.command().accept("");
			}
		}));
		addTest("person", Person::testPerson);
		addTest("complex-number", ComplexNumber::testComplexNumber);
		addTest("point", Point::testPoint);
		addTest("wall", Wall::testWall);
		addTest("cuboid", Cuboid::testCuboid);
		addTest("rectangle", Rectangle::testRectangle);
	}

	private static class TestWrapper implements Consumer<String>{
		Runnable r;
		public TestWrapper(Runnable r){
			this.r = r;
		}
		public void accept(String args){
			r.run();
			System.out.println("test done");
		}
	}

	public void addTest(String name, Runnable r){
		parser.add(new CommandParser.Command(name, new TestWrapper(r)));
	}

	public Consumer<String> getCommand(){
		return parser::parse;
	}
}
