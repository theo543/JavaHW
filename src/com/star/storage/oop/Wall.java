package com.star.storage.oop;

public class Wall {
    private double width, height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = Math.max(0, width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = Math.max(0, height);
    }

    public double getArea() {
        return width * height;
    }

    public Wall() {
        width = 0;
        height = 0;
    }

    public Wall(double width, double height) {
        this.width = width;
        this.height = height;
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
}
