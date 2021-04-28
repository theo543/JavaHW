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
}
