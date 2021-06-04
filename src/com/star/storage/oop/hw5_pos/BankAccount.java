package com.star.storage.oop.hw5_pos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BankAccount {
    private final int iban;
    private final ArrayList<Integer> attachedCardNumbers = new ArrayList<>();
    private BigDecimal balance = new BigDecimal(0);

    public BankAccount(int iban) {
        this.iban = iban;
    }

    public int getIBAN() {
        return iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public ArrayList<Integer> getAttachedCardNumbers() {
        return attachedCardNumbers;
    }

    public void addMoney(BigDecimal money) {
        balance = balance.add(money);
    }

    public void withdrawMoney(BigDecimal money) {
        balance = balance.subtract(money);
    }

    public void attachCard(Integer cardNumber) {
        attachedCardNumbers.add(cardNumber);
    }
}
