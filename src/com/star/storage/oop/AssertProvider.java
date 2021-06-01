package com.star.storage.oop;

public class AssertProvider {
    private static boolean throwsError = false;

    public static boolean getThrowsError() {
        return throwsError;
    }

    public static void setThrowsError(boolean throwsError) {
        AssertProvider.throwsError = throwsError;
    }

    public static void Assert(boolean b) {
        if (!b) {
            System.out.println("Assert failed");
            if (throwsError)
                throw (new AssertionError());
        }
    }
}
