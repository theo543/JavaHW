package com.star.storage.oop.hw3.cars;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Truck extends Car {

    private double percentLoaded;

    public Truck(double angle, double x, double y, double percentLoaded) {
        super(angle, x, y);
        setPercentLoaded(percentLoaded);
    }

    public double getPercentLoaded() {
        return percentLoaded;
    }

    public void setPercentLoaded(double percentLoaded) {
        this.percentLoaded = min(1, max(percentLoaded, 0));
    }

    public double getAcceleration() {
        return super.getAcceleration() * (1 - 0.2 * percentLoaded);//loaded truck accelerates 20% slower
    }

    public double getDeceleration() {
        return super.getAcceleration() * (1 + 0.2 * percentLoaded);//loaded truck stops 20% faster
    }
}
