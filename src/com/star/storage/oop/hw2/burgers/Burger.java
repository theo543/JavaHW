package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class Burger{
	private final Vector<Ingredient> ingredients = new Vector<>();
	private final int basicIngredients;
	public final static int maxAdditions = 4;

	public List<Ingredient> getExtras(){
		return ingredients.subList(basicIngredients, ingredients.size());
	}


	public Burger(BreadType bread, MeatType meat, boolean doubleMeat){
		ingredients.add(bread);
		ingredients.add(meat);
		if(doubleMeat){
			ingredients.add(meat);
			basicIngredients = 3;
		}else{
			basicIngredients = 2;
		}
	}

	//returns space left for extras, won't add if no space left or if e is null
	public int pickExtra(Extra e){
		if(null == e || (maxAdditions + basicIngredients) == ingredients.size())
			return 0;
		ingredients.add(e);
		return maxAdditions - ingredients.size() + basicIngredients;
	}

	//sums up all ingredients
	public BigDecimal getPrice(){
		BigDecimal sum = BigDecimal.ZERO;
		for(Ingredient i : ingredients)
			sum = sum.add(i.getPrice());
		return sum;
	}
}
