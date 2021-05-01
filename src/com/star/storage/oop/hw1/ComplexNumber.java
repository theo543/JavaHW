package com.star.storage.oop.hw1;

public class ComplexNumber{
	private double real, imaginary;

	public ComplexNumber(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal(){
		return real;
	}

	public void setReal(double real){
		this.real = real;
	}

	public double getImaginary(){
		return imaginary;
	}

	public void setImaginary(double imaginary){
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

	public static void testComplexNumber(){
		ComplexNumber one = new ComplexNumber(1.0, 1.0);
		ComplexNumber number = new ComplexNumber(2.5, -1.5);
		one.add(1, 1);
		System.out.println("one.real= " + one.getReal());
		System.out.println("one.imaginary= " + one.getImaginary());
		one.subtract(number);
		System.out.println("one.real= " + one.getReal());
		System.out.println("one.imaginary= " + one.getImaginary());
		number.subtract(one);
		System.out.println("number.real= " + number.getReal());
		System.out.println("number.imaginary= " + number.getImaginary());
		// one.real= 2.0
		// one.imaginary= 2.0
		// one.real= -0.5
		// one.imaginary= 3.5
		// number.real= 3.0
		// number.imaginary= -5.0
	}
}
