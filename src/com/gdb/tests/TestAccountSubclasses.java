package com.gdb.tests;

import com.gdb.domain.*;

public class TestAccountSubclasses {

    public static void main(String[] args) {

        System.out.println("=== Activity 8: Polymorphism Test ===");

        SavingsAccount savings = new SavingsAccount(1001, "John Doe", 25, 10000, 1000, 4);
        savings.setPin(1234);

        try {
            savings.withdraw(9500, 1234);
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): FAILED");
        } catch (AccountException e) {
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): Caught AccountException [PASS]");
        }

        CurrentAccount current = new CurrentAccount(1002, "Alice Brown", 30, 10000, 25000);
        current.setPin(1234);

        try {
            current.withdraw(15000, 1234);
            System.out.println("[Current] Withdraw with Overdraft (Balance goes to -5000): SUCCESS [PASS]");
        } catch (AccountException e) {
            System.out.println("[Current] Withdraw with Overdraft (Balance goes to -5000): FAILED");
        }

        try {
            current.withdraw(20000, 1234);
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): FAILED");
        } catch (AccountException e) {
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): Caught AccountException [PASS]");
        }

        FixedDepositAccount fixedDeposit = new FixedDepositAccount(1003, "Bob Wilson", 35, 10000, 12, 6.5);
        fixedDeposit.setPin(1234);

        try {
            fixedDeposit.withdraw(5000, 1234);
            System.out.println("[FixedDeposit] Withdraw attempt: FAILED");
        } catch (AccountException e) {
            System.out.println("[FixedDeposit] Withdraw attempt: Caught AccountException [PASS]");
        }

        System.out.println("All polymorphic behaviors verified!");
    }
}