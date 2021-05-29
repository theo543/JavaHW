package com.star.storage.oop;

import java.util.Scanner;


public class Main {
    private static boolean exitLoop = false;

    public static void main(String[] args) {
        CommandParser cli = new CommandParser();
        cli.add("exit", (a) -> exitLoop = true);
        cli.add("test", TestCommand::parseTests);
        String input;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a command:");
        while (!exitLoop) {
            input = s.nextLine();
            cli.parse(input);
        }
    }
}
