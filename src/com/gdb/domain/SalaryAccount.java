package com.gdb.domain;

public class SalaryAccount extends AbstractAccount {

    public SalaryAccount(int accountNumber, String name, int age,
                         double balance) {

        super(accountNumber, name, age, balance, "Salary");
    }

    @Override
    protected void processDebit(double amount) throws AccountException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient available balance."
            );
        }

        balance = balance - amount;
    }
}