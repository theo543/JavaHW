package com.star.storage.oop;

import com.star.storage.oop.hw2.burgers.BurgerCommand;
import com.star.storage.oop.hw3.cars.VehicleCommand;

import java.util.Scanner;


public class Main {
    private static boolean exitLoop = false;

    public static void main(String[] args) {
        CommandParser cli = new CommandParser();
        BurgerCommand burgerCli = new BurgerCommand();
        VehicleCommand carCli = new VehicleCommand();
        cli.add("exit", (a) -> exitLoop = true);
        cli.add("test", TestCommand::parseTests);
        cli.add("burger", burgerCli::parse);
        cli.add("car", carCli::parse);
        String input;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a command:");
        while (!exitLoop) {
            input = s.nextLine();
            cli.parse(input);
        }
    }
}
