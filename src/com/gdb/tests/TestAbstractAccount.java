package com.gdb.tests;

import com.gdb.domain.*;

public class TestAbstractAccount {

    public static void main(String[] args) {

        System.out.println(
                "=== Activity 9: Abstract Account & Template Pattern ==="
        );

        // ------------------------------------------------
        // SAVINGS ACCOUNT
        // ------------------------------------------------

        SavingsAccount savings =
                new SavingsAccount(
                        101,
                        "Devansh",
                        19,
                        10000,
                        500,
                        5
                );

        savings.setPin(1234);

        try {

            savings.withdraw(2000, 1234);

            System.out.println(
                    "[Savings] Withdraw 2000: SUCCESS | Balance: Rs "
                    + savings.getBalance()
            );

        } catch (AccountException e) {

            System.out.println(
                    "[Savings] Withdraw 2000: FAILED | "
                    + e.getMessage()
            );
        }


        // Savings minimum balance test
        try {

            savings.withdraw(8000, 1234);

            System.out.println(
                    "[Savings] Withdraw below min balance: FAILED"
            );

        } catch (AccountException e) {

            System.out.println(
                    "[Savings] Withdraw below min balance: "
                    + "Caught MinimumBalanceViolationException [PASS]"
            );
        }


        // ------------------------------------------------
        // CURRENT ACCOUNT
        // ------------------------------------------------

        CurrentAccount current =
                new CurrentAccount(
                        102,
                        "Devansh",
                        19,
                        2000,
                        5000
                );

        current.setPin(1234);

        try {

            current.withdraw(5000, 1234);

            System.out.println(
                    "[Current] Overdraft debit: SUCCESS | Balance: Rs "
                    + current.getBalance()
            );

        } catch (AccountException e) {

            System.out.println(
                    "[Current] Overdraft debit: FAILED | "
                    + e.getMessage()
            );
        }


        // ------------------------------------------------
        // FIXED DEPOSIT
        // ------------------------------------------------

        FixedDepositAccount fd =
                new FixedDepositAccount(
                        103,
                        "Devansh",
                        19,
                        10000,
                        12,
                        7.5
                );

        fd.setPin(1234);

        try {

            fd.withdraw(2000, 1234);

            System.out.println(
                    "[FixedDeposit] Premature debit: FAILED"
            );

        } catch (AccountException e) {

            System.out.println(
                    "[FixedDeposit] Premature debit: "
                    + "Caught AccountException [PASS]"
            );
        }


        System.out.println(
                "Template method pattern executed successfully!"
        );
    }
}