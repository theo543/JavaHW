package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;
import java.util.Vector;

public class DeluxeBurger extends Burger{
	private static final BigDecimal priceOfChipsAndSoda = new BigDecimal("3");
	public final static int maxAdditions = 0;
	private final Vector<Extra> extra = new Vector<>(0);

	public DeluxeBurger(BreadType bread, MeatType meat){
		super(bread, meat, true);
	}

	public BigDecimal getPrice(){
		return super.getPrice().add(priceOfChipsAndSoda);
	}
}
