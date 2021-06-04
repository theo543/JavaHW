package com.star.storage.oop.hw2_burgers;

import java.math.BigDecimal;

public class DeluxeBurger extends Burger {

    public DeluxeBurger(BreadType bread, MeatType meat) {
        super(bread, meat, true);
        ingredients.add(new Chips());
        ingredients.add(new Soda());
    }

    public String getName() {
        return "Deluxe Burger";
    }

    public int getMaxAdditions() {
        return 2;
    }

    //only deluxe burgers get Chips and Soda!
    private static class Chips implements Ingredient {
        public BigDecimal getPrice() {
            return new BigDecimal("1.8");
        }

        public String toString() {
            return "Chips";
        }
    }

    private static class Soda implements Ingredient {
        public BigDecimal getPrice() {
            return new BigDecimal("1.2");
        }

        public String toString() {
            return "Soda";
        }
    }

}
