package com.star.storage.oop.hw8_streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class WallStreet {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");

    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static Stream<Transaction> stream() {
        return transactions.stream();
    }

    /**
     * 1. Find all transactions in the year 2011 and sort them by value (small to high).
     * 2. What are all the unique cities where the traders work?
     * 3. Find all traders from Cambridge and sort them by name.
     * 4. Return a string of all traders’ names sorted alphabetically.
     * 5. Are any traders based in Milan?
     * 6. Print all transactions’ values from the traders living in Cambridge.
     * 7. What’s the highest value of all the transactions?
     * 8. Find the transaction with the smallest value.
     */
    public static void main(String[] args) {
        System.out.println("2011 sorted:");
        stream().filter((a) -> a.year == 2011).sorted(Comparator.comparingInt(a -> a.value)).forEach(System.out::println);
        System.out.println("\nCities:");
        stream().map((a) -> a.trader.city).distinct().forEach(System.out::println);
        System.out.println("\nTraders from Cambridge sorted:");
        stream().map((a) -> a.trader).distinct().filter((a) -> a.city.equals("Cambridge")).sorted(Comparator.comparing((a) -> a.name)).forEach(System.out::println);
        System.out.println("\nTrader names sorted:");
        stream().map((a) -> a.trader.name).distinct().sorted().forEach(System.out::println);
        System.out.println(stream().anyMatch((a) -> a.trader.city.equals("Milan")) ? "\nThere are traders in Milan" : "\nNo traders in Milan");
        System.out.println("\nValues from Cambridge:");
        stream().filter((a) -> a.trader.city.equals("Cambridge")).map((a) -> a.value).forEach(System.out::println);
        System.out.println("\nHighest value:");
        stream().sorted(Comparator.comparingInt((a) -> -a.value)).limit(1).forEach((a) -> System.out.println(a.value));
        System.out.println("\nSmallest transaction:");
        stream().sorted(Comparator.comparingInt((a) -> a.value)).limit(1).forEach(System.out::println);
    }

    public record Trader(String name, String city) {
    }

    public record Transaction(Trader trader, int year, int value) {
    }
}
