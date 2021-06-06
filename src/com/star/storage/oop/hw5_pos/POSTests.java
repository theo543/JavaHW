package com.star.storage.oop.hw5_pos;

import java.math.BigDecimal;
import java.util.Calendar;

import static com.star.storage.oop.AssertProvider.Assert;

public class POSTests {
    public static void runTests() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 5);
        POSService pos = new POSService();
        User u = new User();
        BankAccount a = new BankAccount(11);
        u.addCard(new Card(c.getTime(), "test", 1));
        //unknown card test
        Assert(pos.pay(new BigDecimal("-0.01"), u.cards.get(0)).equals("Card not attached to any known account"));
        //normal test
        a.attachCard(1);
        a.addMoney(new BigDecimal("1.3"));
        pos.addKnownBankAccount(a);
        Assert(pos.pay(new BigDecimal("0.9"), u.cards.get(0)).equals("Success: amount = 0.9€"));
        Assert(a.getBalance().equals(new BigDecimal("0.4")));
        //no money test
        Assert(pos.pay(new BigDecimal("0.41"), u.cards.get(0)).equals("Insufficient money"));
        Assert(a.getBalance().equals(new BigDecimal("0.4")));
        //expired test
        c.add(Calendar.YEAR, -50);
        u.addCard(new Card(c.getTime(), "expired", 2));
        a.attachCard(2);
        Assert(pos.pay(new BigDecimal("0.1"), u.cards.get(1)).equals("Card is expired"));
    }
}
