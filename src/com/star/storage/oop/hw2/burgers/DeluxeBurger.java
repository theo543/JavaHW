package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;
import java.util.Vector;

public class DeluxeBurger extends Burger{
	//only deluxe burgers get Chips and Soda!
	private class Chips implements Ingredient{
		public BigDecimal getPrice(){
			return new BigDecimal("1.8");
		}
		public String toString(){
			return "Chips";
		}
	}
	private class Soda implements Ingredient{
		public BigDecimal getPrice(){
			return new BigDecimal("1.2");
		}
		public String toString(){
			return "Soda";
		}
	}
	public final static int maxAdditions = 2;
	private final Vector<Ingredient> ingredients = new Vector<>(2);

	public DeluxeBurger(BreadType bread, MeatType meat){
		super(bread, meat, true);
		ingredients.add(new Chips());
		ingredients.add(new Soda());
	}

}
