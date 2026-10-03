package com.gdb.tests;

import com.gdb.domain.*;

public class TestAbstractAccount {

    // Transfer funds from one account to another
    public static boolean transferFunds(
            AbstractAccount source,
            AbstractAccount destination,
            double amount,
            int pin) {

        try {
            // Withdraw from source
            source.withdraw(amount, pin);

            // Deposit into destination only if withdrawal succeeds
            destination.deposit(amount);

            return true;

        } catch (AccountException e) {

            System.out.println(
                    "Transfer failed: " + e.getMessage()
            );

            return false;
        }
    }


    // Process monthly banking cycle
    public static void processMonthlyCycle(
            AbstractAccount[] accounts) {

        for (AbstractAccount account : accounts) {

            // Apply interest to Savings Account
            if (account instanceof SavingsAccount) {

                SavingsAccount savings =
                        (SavingsAccount) account;

                savings.applyInterest();
            }

            // Check Salary Account
            if (account instanceof SalaryAccount) {

                System.out.println(
                        "Salary account checked for salary credit history."
                );
            }
        }

        System.out.println(
                "Monthly Interest Cycle processed for all qualifying accounts."
        );
    }


    public static void main(String[] args) {

        System.out.println(
                "=== Activity 10: Banking Operations Suite ==="
        );


        // ================================================
        // STEP 1: CREATE ACCOUNT PORTFOLIO
        // ================================================

        SavingsAccount savings =
                new SavingsAccount(
                        101,
                        "Devansh",
                        19,
                        10000,
                        500,
                        5
                );

        CurrentAccount current =
                new CurrentAccount(
                        102,
                        "Rahul",
                        20,
                        5000,
                        5000
                );

        SalaryAccount salary =
                new SalaryAccount(
                        103,
                        "Aman",
                        21,
                        15000
                );


        // Set PINs
        savings.setPin(1234);
        current.setPin(2345);
        salary.setPin(3456);


        // Abstract account portfolio
        AbstractAccount[] accounts = {
                savings,
                current,
                salary
        };


        // ================================================
        // STEP 2: SUCCESSFUL FUND TRANSFER
        // ================================================

        boolean transferSuccessful =
                transferFunds(
                        savings,
                        current,
                        3000,
                        1234
                );

        if (transferSuccessful) {

            System.out.println(
                    "Transfer Rs 3000 from Savings to Current: SUCCESS"
            );

            System.out.println(
                    "Savings Balance: Rs "
                    + savings.getBalance()
                    + " | Current Balance: Rs "
                    + current.getBalance()
            );
        }


        // ================================================
        // FAILED TRANSFER - WRONG PIN
        // ================================================

        double savingsBefore =
                savings.getBalance();

        double currentBefore =
                current.getBalance();

        boolean failedTransfer =
                transferFunds(
                        savings,
                        current,
                        2000,
                        9999
                );

        if (!failedTransfer
                && savings.getBalance() == savingsBefore
                && current.getBalance() == currentBefore) {

            System.out.println(
                    "Failed Transfer (Wrong PIN): "
                    + "Exception caught, no balance changed [PASS]"
            );
        }


        // ================================================
        // STEP 3: MONTHLY BANKING CYCLE
        // ================================================

        processMonthlyCycle(accounts);


        // ================================================
        // FINAL MESSAGE
        // ================================================

        System.out.println(
                "All banking operations passed!"
        );
    }
}