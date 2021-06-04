package com.star.storage.oop.hw4_video;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Video {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    static {
        df.setRoundingMode(RoundingMode.FLOOR);
    }

    private final String title;
    private boolean checkedOut = false;
    private double rating = 5;
    private int ratings = 0;

    public Video(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public void updateRating(double d) {
        rating = (ratings * rating + Math.max(1, Math.min(10, d))) / (ratings + 1);
        ratings++;
    }

    public String toString() {
        return title + "(" + df.format(rating) + "/10)" + (checkedOut ? " - UNAVAILABLE" : " - AVAILABLE");
    }
}
