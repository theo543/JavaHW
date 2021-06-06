package com.star.storage.oop;

import com.star.storage.oop.hw1.ComplexNumber;
import com.star.storage.oop.hw1.Person;
import com.star.storage.oop.hw1.Point;
import com.star.storage.oop.hw1.Wall;
import com.star.storage.oop.hw3_shapes.Cuboid;
import com.star.storage.oop.hw3_shapes.Rectangle;
import com.star.storage.oop.hw5_pos.POSTests;

import java.util.HashSet;
import java.util.Set;

public final class TestCommand {
    private static final Autocomplete<Runnable> tests = new Autocomplete<>();

    static {
        addTest("person", Person::testPerson);
        addTest("complex-number", ComplexNumber::testComplexNumber);
        addTest("point", Point::testPoint);
        addTest("wall", Wall::testWall);
        addTest("cuboid", Cuboid::testCuboid);
        addTest("rectangle", Rectangle::testRectangle);
        addTest("pos", POSTests::runTests);
    }

    private TestCommand() {
    }

    public static void addTest(String name, Runnable r) {
        tests.add(name, () -> {
            r.run();
            System.out.println(name + " test done");
        });
    }

    public static void parseTests(String[] s) {
        if (s.length == 0) {
            tests.list().forEach((a) -> a.data().run());
            return;
        }
        Set<Runnable> t = new HashSet<>();
        for (var i : s) {
            for (var j : tests.get(i)) {
                t.add(j.data());
            }
        }
        t.forEach(Runnable::run);
    }
}
