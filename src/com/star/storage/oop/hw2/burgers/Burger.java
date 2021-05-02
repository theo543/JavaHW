package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class Burger{
	protected final Vector<Ingredient> ingredients = new Vector<>();
	private final int basicIngredients;

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

	public String getName(){
		return "Burger";
	}

	public int getMaxAdditions(){
		return 4;
	}

	public List<Ingredient> getExtras(){
		return ingredients.subList(basicIngredients, ingredients.size());
	}

	//returns space left for extras, won't add if no space left or if e is null
	public int pickExtra(Extra e){
		if(null == e || (getMaxAdditions() + basicIngredients) == ingredients.size())
			return 0;
		ingredients.add(e);
		return getMaxAdditions() - ingredients.size() + basicIngredients;
	}

	//sums up all ingredients
	public BigDecimal getPrice(){
		BigDecimal sum = BigDecimal.ZERO;
		for(Ingredient i : ingredients)
			sum = sum.add(i.getPrice());
		return sum;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder(getName().toUpperCase());
		sb.append(":\n");
		for(Ingredient i : ingredients){
			String[] iNameList = i.toString().split("_");
			for(String s : iNameList){
				sb.append(s.toUpperCase().charAt(0));
				sb.append(s.substring(1).toLowerCase());
				sb.append(" ");
			}
			sb.setLength(sb.length() - 1);//remove last space
			sb.append(": ");
			sb.append(i.getPrice());
			sb.append("$ \n");
		}
		sb.append("No Extra\n".repeat(getMaxAdditions() - ingredients.size() + basicIngredients));
		sb.append("TOTAL: ").append(getPrice().toString()).append("$\n");
		return sb.toString();
	}
}
