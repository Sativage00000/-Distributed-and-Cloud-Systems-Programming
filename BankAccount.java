package com.example;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class BankAccount extends AbstractActor {
    private double balance = 100.0;

    static public Props props() {
        return Props.create(BankAccount.class, () -> new BankAccount());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, deposit -> {
                    balance += deposit.getAmount();
                    System.out.println("Deposit: " + deposit.getAmount() + ", New Balance: " + balance);
                })
                .match(Withdrawal.class, withdrawal -> {
                    balance -= withdrawal.getAmount();
                    System.out.println("Withdrawal: " + withdrawal.getAmount() + ", New Balance: " + balance);
                })
                .build();
    }
}
