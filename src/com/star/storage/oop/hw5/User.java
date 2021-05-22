package com.star.storage.oop.hw5;

import java.util.ArrayList;

public class User {
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
}
