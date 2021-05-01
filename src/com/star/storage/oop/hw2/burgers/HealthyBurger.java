package com.star.storage.oop.hw2.burgers;

public class HealthyBurger extends Burger{
	public final static int maxAdditions = 6;

	public HealthyBurger(MeatType meat){
		super(BreadType.BROWN_RYE, meat, false);
	}
}
