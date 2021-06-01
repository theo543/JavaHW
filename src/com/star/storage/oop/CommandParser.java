package com.star.storage.oop;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public class CommandParser {
    private final Autocomplete<Consumer<String[]>> list = new Autocomplete<>();
    private final Runnable defaultCommand = () -> helpCommand(new String[]{});

    public CommandParser() {
        add("help", this::helpCommand);
    }

    private void helpCommand(String[] args) {
        System.out.println("Available commands:");
        for (var c : list.list()) {
            System.out.println(c.name());
        }
    }

    public void add(String name, Consumer<String[]> c) {
        list.add(name, c);
    }

    public void parse(String input) {
        input = input.strip();
        if (input.isEmpty()) {
            defaultCommand.run();
            return;
        }
        var match = Pattern.compile("^([-a-z0-9_]+)(?![^ ])").matcher(input);
        if (!match.find()) {
            System.out.println("Command not found");
            return;
        }
        var c = list.get(match.group(0));
        if (c.size() == 0) {
            System.out.println("Command not found");
        } else if (c.size() > 1) {
            System.out.println("Command ambiguous:");
            for (var v : c)
                System.out.println(v.name());
        } else {
            try {
                var args = input.substring(match.end(0)).strip().split(" +");
                if (args.length == 1 && args[0].equals(""))
                    args = new String[]{};
                c.get(0).data().accept(args);
            } catch (Exception e) {
                System.out.println("\u001B[31m" + e.getClass().toString() + "\u001B[0m");//color codes
                ///TODO Print command format
            }
        }
    }
}
