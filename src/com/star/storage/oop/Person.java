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
}