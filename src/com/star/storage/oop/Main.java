package com.star.storage.oop;

import com.star.storage.oop.hw2.burgers.BurgerCommand;
import com.star.storage.oop.hw3.cars.CarCommand;

import java.util.Scanner;


public class Main {
    private static boolean exitLoop = false;

    public static void main(String[] args) {
        CommandParser cli = new CommandParser();
        BurgerCommand burgerCli = new BurgerCommand();
        CarCommand carCli = new CarCommand();
        cli.add("exit", (a) -> exitLoop = true);
        cli.add("test", TestCommand::parseTests);
        cli.add("burger", burgerCli::parse);
        cli.add("car", carCli::parse);
        String input;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a command:");
        while (true) {
            input = s.nextLine();
            cli.parse(input);
            if (exitLoop) {
                System.exit(0);
                break;
            }
        }
    }
}
