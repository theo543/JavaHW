package com.star.storage.oop.hw3.shapes;

import static com.star.storage.oop.AssertProvider.Assert;
import static java.lang.Math.max;

public class Rectangle {
    private final double width;
    private final double length;

    public Rectangle(double width, double length) {
        this.width = max(0, width);
        this.length = max(0, length);
    }

    public static void testRectangle() {
        Rectangle rectangle = new Rectangle(5, 10);
        Assert(rectangle.getWidth() == 5);
        Assert(rectangle.getLength() == 10);
        Assert(rectangle.getArea() == 50);
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getArea() {
        return width * length;
    }
}
