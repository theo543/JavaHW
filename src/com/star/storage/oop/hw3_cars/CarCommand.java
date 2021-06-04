package com.star.storage.oop.hw3_cars;

import com.star.storage.oop.CommandParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static java.lang.Math.*;

public class CarCommand {
    private final CommandParser parser = new CommandParser();
    private Car car;

    public CarCommand() {
        parser.add("new-car", this::newCar);
        parser.add("new-truck", this::newTruck);
        addSafely("status", this::status);
        addSafely("set-speed", this::setSpeed);
        addSafely("move", this::move);
        addSafely("set-loaded", this::setLoaded);
        addSafely("steer-left", (a) -> steer(a, false));
        addSafely("steer-right", (a) -> steer(a, true));
        addSafely("print-movements", this::printMovements);
        addSafely("display-path", this::display);
        addSafely("save-path", this::savePath);
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
        f.add(new PathPanel(car));
        f.setResizable(false);
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        l = max(l, max(f.getBounds().width, f.getBounds().height));
        f.setSize(l, l);
        f.setLocationRelativeTo(null);
        f.toFront();
    }

    private void savePath(String[] args) {
        int w, h;
        if (args.length == 0)
            w = h = 500;
        else {
            w = Integer.parseInt(args[0]);
            h = Integer.parseInt(args[1]);
        }
        var p = new PathPanel(car);
        p.setSize(w, h);
        BufferedImage i = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_ARGB);
        p.paint(i.getGraphics());
        JFileChooser f = new JFileChooser();
        f.setDialogType(JFileChooser.SAVE_DIALOG);
        f.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".png");
            }

            public String getDescription() {
                return "PNG";
            }
        });
        JFrame fr = new JFrame();
        f.addActionListener((a) -> {
            if (a.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                try {
                    ImageIO.write(i, "png", f.getSelectedFile());
                    fr.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (a.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
                fr.dispose();
        });
        fr.add(f);
        fr.setAlwaysOnTop(true);
        fr.toFront();
        fr.pack();
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }

    private void printMovements(String[] args) {
        for (var move : car.getMovements()) {
            System.out.print("To " + move.x + "," + move.y);
            if (move.sData != null)
                System.out.println(", angle " + toDegrees(move.angle) + "°, " + (move.sData.right() ? "right" : "left"));
            else System.out.println();
        }
    }

    private void steer(String[] args, boolean dir) {
        car.steer(toRadians(max(0, Double.parseDouble(args[0]))), dir);
    }

    private void status(String[] args) {
        System.out.println("x = " + car.getX() + "m");
        System.out.println("y = " + car.getY() + "m");
        System.out.println("angle = " + toDegrees(car.getAngle()) + "°");
        System.out.println("speed = " + car.getSpeed() + " mps");
        if (car instanceof Truck)
            System.out.println("truck load = " + ((Truck) car).getPercentLoaded() * 100.0 + "%");
    }

    private void setLoaded(String[] args) {
        if (!(car instanceof Truck)) {
            System.out.println("Vehicle is not a truck");
        } else {
            ((Truck) car).setPercentLoaded(Double.parseDouble(args[0]));
        }
    }

    private void setSpeed(String[] args) {
        car.changeSpeed(Double.parseDouble(args[0]));
    }

    private void move(String[] args) {
        car.move(Double.parseDouble(args[0]));
    }

    private void newCar(String[] args) {
        if (args.length == 0) {
            car = new Car(0, 0, 0);
            return;
        }
        double a, x, y;
        a = Double.parseDouble(args[0]);
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
        car = new Car(a, x, y);
    }

    private void newTruck(String[] args) {
        if (args.length == 0) {
            car = new Truck(0, 0, 0, 0);
            return;
        }
        double a, x, y, l;
        a = Double.parseDouble(args[0]);
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
        l = min(1, max(0, Double.parseDouble(args[3])));
        car = new Truck(a, x, y, l);
    }

    public void addSafely(String name, Consumer<String[]> command) {
        parser.add(name, (args) -> {
            if (car == null)
                System.out.println("Vehicle not created yet");
            else command.accept(args);
        });
    }

    public void parse(String[] args) {
        parser.parse(String.join(" ", args));
    }
}
