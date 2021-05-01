package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;
import java.util.Vector;

public class Burger{
	private Vector<Extra> extra;
	private BreadType bread;
	private MeatType meat;
	private boolean doubleMeat;
	public final static int maxAdditions = 4;

	public BreadType getBread(){
		return bread;
	}

	public MeatType getMeat(){
		return meat;
	}

	public Vector<Extra> getExtra(){
		return extra;
	}

	public boolean hasDoubleMeat(){
		return doubleMeat;
	}

	public Burger(BreadType bread, MeatType meat, boolean doubleMeat){
		this.bread = bread;
		this.meat = meat;
		this.doubleMeat = doubleMeat;
		this.extra = new Vector<>();
	}

	//returns space left for extras, won't add if full or if e is null
	public int pickExtra(Extra e){
		if(null == e || maxAdditions == extra.size())
			return 0;
		extra.add(e);
		return maxAdditions - extra.size();
	}

	//sums up all ingredients
	public BigDecimal getPrice(){
		BigDecimal sum = BigDecimal.ZERO;
		for(Extra i : extra)
			sum = sum.add(i.getPrice());
		return bread.getPrice().add(meat.getPrice()).add(doubleMeat ? meat.getPrice() : BigDecimal.ZERO).add(sum);
	}
}
