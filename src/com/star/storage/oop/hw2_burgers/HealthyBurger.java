package com.star.storage.oop.hw2_burgers;

public class HealthyBurger extends Burger {

    public HealthyBurger(MeatType meat) {
        super(BreadType.BROWN_RYE, meat, false);
    }

    public int getMaxAdditions() {
        return 6;
    }

    public String getName() {
        return "Healthy Burger";
    }
}
