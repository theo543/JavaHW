package com.star.storage.oop.hw2.burgers;

import java.math.BigDecimal;

public enum MeatType implements Ingredient {
    PORK(new BigDecimal(("3.3"))),
    BEEF(new BigDecimal(("3.1"))),
    CHICKEN(new BigDecimal(("3.5")));

    private final BigDecimal price;

    MeatType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
