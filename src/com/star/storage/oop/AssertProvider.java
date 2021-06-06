package com.star.storage.oop;

public class AssertProvider {
    public static void Assert(boolean b) {
        if (!b) {
            System.out.println("Assert failed");
            if (CommandParser.allowExceptions)
                throw (new AssertionError());
        }
    }
}
