package com.star.storage.oop.hw1;

import static com.star.storage.oop.AssertProvider.Assert;

public class ComplexNumber {
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static void testComplexNumber() {
        ComplexNumber one = new ComplexNumber(1.0, 1.0);
        ComplexNumber number = new ComplexNumber(2.5, -1.5);
        one.add(1, 1);
        Assert(one.real == 2.0);
        Assert(one.imaginary == 2.0);
        one.subtract(number);
        Assert(one.real == -0.5);
        Assert(one.imaginary == 3.5);
        number.subtract(one);
        Assert(number.real == 3.0);
        Assert(number.imaginary == -5.0);

    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public void add(double real, double imaginary) {
        this.real += real;
        this.imaginary += imaginary;
    }

    public void subtract(double real, double imaginary) {
        add(-real, -imaginary);
    }

    public void add(ComplexNumber cn) {
        add(cn.real, cn.imaginary);
    }

    public void subtract(ComplexNumber cn) {
        subtract(cn.real, cn.imaginary);
    }
}
