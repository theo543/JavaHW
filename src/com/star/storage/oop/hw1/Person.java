package com.star.storage.oop.hw1;

import static com.star.storage.oop.Main.Assert;

public class Person{
	private String firstName, lastName;
	private int age;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public int getAge(){
		return age;
	}

	public void setFirstName(String name){
		firstName = name;
	}

	public void setLastName(String name){
		lastName = name;
	}

	public void setAge(int new_age){
		age = new_age;
		if((age > 100) || (age < 0))
			age = 0;
	}

	public boolean isTeen(){
		return ((age > 12) && (age < 20));
	}

	public String getFullName(){
		String full = firstName + " " + lastName;
		return full.trim();
	}

	public static void testPerson(){
		Person person = new Person();
		person.setFirstName("");
		person.setLastName("");
		person.setAge(10);
		Assert(person.getFullName().equals(""));
		Assert(!person.isTeen());
		person.setFirstName("John");
		person.setAge(18);
		Assert(person.getFullName().equals("John"));
		Assert(person.isTeen());
		person.setLastName("Smith");
		Assert(person.getFullName().equals("John Smith"));
		Assert(person.getFullName().equals(person.getFirstName() + " " + person.getLastName()));
	}
}