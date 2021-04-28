package com.star.storage.oop;

public class ComplexNumber {
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
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

    public void add(double real, double imaginary){
        this.real += real;
        this.imaginary += imaginary;
    }

    public void subtract(double real, double imaginary){
        add(-real, -imaginary);
    }

    public void add(ComplexNumber cn){
        add(cn.real, cn.imaginary);
    }

    public void subtract(ComplexNumber cn){
        subtract(cn.real, cn.imaginary);
    }
}
