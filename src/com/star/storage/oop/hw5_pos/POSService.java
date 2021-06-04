package com.star.storage.oop.hw5_pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class POSService {
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public void addKnownBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public String pay(BigDecimal amount, Card card) {
        BankAccount account = null;
        main:
        for (var a : bankAccounts) {
            for (var c : a.getAttachedCardNumbers()) {
                if (c.equals(card.cardNumber())) {
                    account = a;
                    break main;
                }
            }
        }
        if (account == null) {
            return "Card not attached to any known account";
        }
        if (new Date().after(card.expirationDate()))
            return "Card is expired";
        if (account.getBalance().compareTo(amount) < 0)
            return "Insufficient money";
        account.withdrawMoney(amount);
        return "Success: amount = " + amount.toString() + "€";
    }
}
