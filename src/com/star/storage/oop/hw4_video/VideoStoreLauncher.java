package com.star.storage.oop.hw4_video;

import java.util.Random;

public class VideoStoreLauncher {
    public static void main(String[] args) {
        VideoStore vs = new VideoStore();
        String[] list = new String[]{"The Matrix", "Godfather II", "Star Wars Episode IV: A New Hope"};
        for (String v : list) {
            vs.addVideo(v);
        }
        var r = new Random();
        for (int x = 0; x < 1000; x++)
            r.nextInt();
        for (int x = 0; x <= 50; x++) {
            vs.receiveRating(list[r.nextInt(3)], r.nextInt(3) + 8);
        }
        for (int x = 0; x <= 10; x++) {
            vs.receiveRating(list[r.nextInt(3)], r.nextInt(5));
        }
        vs.receiveRating(list[r.nextInt(3)], Integer.MIN_VALUE);
        vs.receiveRating(list[r.nextInt(3)], Integer.MAX_VALUE);
        for (String v : list) {
            vs.checkOut(v);
            vs.returnVideo(v);
        }
        vs.checkOut("Godfather II");
        vs.checkOut("TEST 123");
        vs.returnVideo("TEST 321");
        vs.receiveRating("TEST 213", Integer.MAX_VALUE);
        vs.listVideos();
    }
}
