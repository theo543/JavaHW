package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;

public enum BreadType implements Ingredient{
	SIMPLE(new BigDecimal("1")),
	POTATO(new BigDecimal("2")),
	BROWN_RYE(new BigDecimal("2.3")),
	WHOLE_WHEAT(new BigDecimal("1.97")),
	MULTIGRAIN(new BigDecimal("2.13"))
	;

	private final BigDecimal price;

	public BigDecimal getPrice(){
		return price;
	}

	BreadType(BigDecimal price){
		this.price = price;
	}
}
