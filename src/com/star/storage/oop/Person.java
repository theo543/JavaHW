package com.star.storage.oop;

public class Person {
    private String firstName, lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public void setAge(int new_age) {
        age = new_age;
        if ((age > 100) || (age < 0))
            age = 0;
    }

    public boolean isTeen() {
        return ((age > 12) && (age < 20));
    }

    public String getFullName() {
        String full = firstName + " " + lastName;
        return full.trim();
    }

    public static void testPerson() {
        Person person = new Person();
        person.setFirstName("");   // firstName is set to empty string
        person.setLastName("");    // lastName is set to empty string
        person.setAge(10);
        System.out.println("fullName= " + person.getFullName());
        System.out.println("teen= " + person.isTeen());
        person.setFirstName("John");    // firstName is set to John
        person.setAge(18);
        System.out.println("fullName= " + person.getFullName());
        System.out.println("teen= " + person.isTeen());
        person.setLastName("Smith");    // lastName is set to Smith
        System.out.println("fullName= " + person.getFullName());
        // fullName=
        // teen= true
        // fullName= John
        // teen= true
        // fullName= John Smith
    }
}