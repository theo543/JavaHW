package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;

public enum Extra{
	LETTUCE(new BigDecimal("0.6")),
	FRIES(new BigDecimal("1.3")),
	CUCUMBERS(new BigDecimal("0.7")),
	TOMATOES(new BigDecimal("0.75")),
	CHEESE(new BigDecimal("0.9")),
	CARROT(new BigDecimal("0.76"))
	;

	private BigDecimal price;

	public BigDecimal getPrice(){
		return price;
	}

	Extra(BigDecimal price){
		this.price = price;
	}
}
