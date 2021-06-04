package com.star.storage.oop.hw3.cars;

import com.star.storage.oop.CommandParser;

import javax.swing.*;
import java.util.function.Consumer;

import static java.lang.Math.*;

public class VehicleCommand {
    private final CommandParser parser = new CommandParser();
    private Car vehicle;

    public VehicleCommand() {
        parser.add("new-car", this::newCar);
        parser.add("new-truck", this::newTruck);
        addSafely("status", this::status);
        addSafely("set-speed", this::setSpeed);
        addSafely("move", this::move);
        addSafely("set-loaded", this::setLoaded);
        addSafely("steer-left", this::steerLeft);
        addSafely("steer-right", this::steerRight);
        addSafely("print-movements", this::printMovements);
        addSafely("display", this::display);
        parser.add("arc-test", (args) -> {
            parser.parse("new-car");
            parser.parse("set-speed 10");
            parser.parse("display");
            parser.parse("steer-right 90");
            parser.parse("set-speed 20");
            parser.parse("steer-left 210");
        });
    }

    private void display(String[] args) {
        int l;
        if (args.length == 0)
            l = 500;
        else l = Integer.parseInt(args[0]);
        JFrame f = new JFrame();
        f.add(new PathPanel(vehicle));
        f.setResizable(false);
        f.setAlwaysOnTop(true);
        f.setVisible(true);
        l = max(l, max(f.getBounds().width, f.getBounds().height));
        f.setSize(l, l);
        f.setLocationRelativeTo(null);
    }

    private void printMovements(String[] args) {
        for (var move : vehicle.getMovements()) {
            System.out.print("To " + move.x + "," + move.y);
            if (move.isArc)
                System.out.println(", angle " + toDegrees(move.angle) + "°, " + (move.right ? "right" : "left"));
            else System.out.println();
        }
    }

    private void steerLeft(String[] args) {
        vehicle.steer(toRadians(Double.parseDouble(args[0])), false);
    }

    private void steerRight(String[] args) {
        vehicle.steer(toRadians(Double.parseDouble(args[0])), true);
    }

    private void status(String[] args) {
        System.out.println("x = " + vehicle.getX() + "m");
        System.out.println("y = " + vehicle.getY() + "m");
        System.out.println("angle = " + toDegrees(vehicle.getAngle()) + "°");
        System.out.println("speed = " + vehicle.getSpeed() + " mps");
        if (vehicle instanceof Truck)
            System.out.println("truck load = " + ((Truck) vehicle).getPercentLoaded() * 100.0 + "%");
    }

    private void setLoaded(String[] args) {
        if (!(vehicle instanceof Truck)) {
            System.out.println("Vehicle is not a truck");
        } else {
            ((Truck) vehicle).setPercentLoaded(Double.parseDouble(args[0]));
        }
    }

    private void setSpeed(String[] args) {
        vehicle.changeSpeed(Double.parseDouble(args[0]));
    }

    private void move(String[] args) {
        vehicle.move(Double.parseDouble(args[0]));
    }

    private void newCar(String[] args) {
        if (args.length == 0) {
            vehicle = new Car(0, 0, 0);
            return;
        }
        double a, x, y;
        a = Double.parseDouble(args[0]);
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
        vehicle = new Car(a, x, y);
    }

    private void newTruck(String[] args) {
        if (args.length == 0) {
            vehicle = new Truck(0, 0, 0, 0);
            return;
        }
        double a, x, y, l;
        a = Double.parseDouble(args[0]);
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
        l = min(1, max(0, Double.parseDouble(args[3])));
        vehicle = new Truck(a, x, y, l);
    }

    public void addSafely(String name, Consumer<String[]> command) {
        parser.add(name, (args) -> {
            if (vehicle == null)
                System.out.println("Vehicle not created yet");
            else command.accept(args);
        });
    }

    public void parse(String[] args) {
        parser.parse(String.join(" ", args));
    }
}
