package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("bank-system");
        ActorRef bankAccount = system.actorOf(BankAccount.props(), "bank-account");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double transactionAmount = random.nextDouble() * 2000 - 1000;
            if (transactionAmount > 0) {
                bankAccount.tell(new Deposit(transactionAmount), ActorRef.noSender());
            } else {
                bankAccount.tell(new Withdrawal(-transactionAmount), ActorRef.noSender());
            }
        }

        // Gracefully shutdown the system after all transactions are done
        system.terminate();
    }
}
