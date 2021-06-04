package com.star.storage.oop.hw2_burgers;

import com.star.storage.oop.Autocomplete;
import com.star.storage.oop.CommandParser;

import java.util.function.Consumer;

public class BurgerCommand {
    private static final Autocomplete<Extra> extras = new Autocomplete<>();
    private static final Autocomplete<BreadType> breads = new Autocomplete<>();
    private static final Autocomplete<MeatType> meats = new Autocomplete<>();

    static {
        for (var v : BreadType.values()) {
            breads.add(v.toString().toLowerCase(), v);
        }
        for (var v : MeatType.values()) {
            meats.add(v.toString().toLowerCase(), v);
        }
        for (var v : Extra.values()) {
            extras.add(v.toString().toLowerCase(), v);
        }
    }

    private final CommandParser parser = new CommandParser();
    private Burger burger;

    public BurgerCommand() {
        parser.add("new-normal", this::newNormal);
        parser.add("new-healthy", this::newHealthy);
        parser.add("new-deluxe", this::newDeluxe);
        addSafely("list", this::listIngredients);
        addSafely("show", this::printBurger);
        addSafely("add-extras", this::addExtras);
    }

    private void addSafely(String name, Consumer<String[]> command) {
        parser.add(name, (args) -> {
            if (burger == null) {
                System.out.println("Burger not created yet");
            } else command.accept(args);
        });
    }

    private void addExtras(String[] args) {
        for (var v : args) {
            if (burger.getExtras().size() == burger.getMaxAdditions()) {
                System.out.println("No space left in burger");
                return;
            } else if (extras.getOne(v) == null)
                System.out.println(v + " not found");
            else {
                burger.pickExtra(extras.getOne(v).data());
                System.out.println(extras.getOne(v).name() + " added");
            }
        }
    }

    private void printBurger(String[] args) {
        System.out.println(burger);
    }

    private void listIngredients(String[] args) {
        System.out.println("Bread types:");
        for (var v : breads.list())
            System.out.print(v.data().toString().toLowerCase() + ", ");
        System.out.println("\nMeat types:");
        for (var v : meats.list())
            System.out.print(v.data().toString().toLowerCase() + ", ");
        System.out.println("\nExtras:");
        for (var v : extras.list())
            System.out.print(v.data().toString().toLowerCase() + ", ");
        System.out.println();
    }

    private void newHealthy(String[] args) {
        if (args.length == 0 || meats.getOne(args[0]) == null)
            System.out.println("Meat type not found");
        else {
            burger = new HealthyBurger(meats.getOne(args[0]).data());
            System.out.println("Healthy burger created");
        }
    }

    private void newDeluxe(String[] args) {
        if (args.length < 2)
            System.out.println("Bread and meat type required");
        else if (breads.getOne(args[0]) == null)
            System.out.println("Bread '" + args[0] + "' not found");
        else if (meats.getOne(args[1]) == null)
            System.out.println("Meat '" + args[1] + "' not found");
        else {
            burger = new DeluxeBurger(breads.getOne(args[0]).data(), meats.getOne(args[1]).data());
            System.out.println("Deluxe burger created");
        }
    }

    private void newNormal(String[] args) {
        if (args.length < 2)
            System.out.println("Bread and meat type required");
        else if (breads.getOne(args[0]) == null)
            System.out.println("Bread '" + args[0] + "' not found");
        else if (meats.getOne(args[1]) == null)
            System.out.println("Meat '" + args[1] + "' not found");
        else {
            boolean choice = false;
            if (args.length < 3)
                System.out.println("Note: Add yes/y to your command for double meat");
            else choice = "yes".startsWith(args[2].toLowerCase());
            burger = new Burger(breads.getOne(args[0]).data(), meats.getOne(args[1]).data(), choice);
            System.out.println("Normal burger created, " + (choice ? "" : "no ") + "double meat");
        }
    }

    public void parse(String[] args) {
        parser.parse(String.join(" ", args));
    }
}
