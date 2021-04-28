package com.star.storage.oop;

public class Main {
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

    public static void testWall() {
        Wall wall = new Wall(5, 4);
        System.out.println("area= " + wall.getArea());
        wall.setHeight(-1.5);
        System.out.println("width= " + wall.getWidth());
        System.out.println("height= " + wall.getHeight());
        System.out.println("area= " + wall.getArea());
        // area= 20.0
        // width= 5.0
        // height= 0.0
        // area= 0.0
    }

    public static void testPoint() {
        Point first = new Point(6, 5);
        Point second = new Point(3, 1);
        System.out.println("distance(0,0)= " + first.distance());
        System.out.println("distance(second)= " + first.distance(second));
        Point point = new Point();
        System.out.println("distance()= " + point.distance());
        // distance(0,0)= 7.810249675906654
        // distance(second)= 5.0
        // distance()= 0.0
    }


    public static void main(String[] args) {
        testPerson();
        testWall();
        testPoint();
    }
}
