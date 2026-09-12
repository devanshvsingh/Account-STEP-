package com.gdb.domain;

public class SavingsAccount extends AccountEnhanced {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String name, int age, double balance, double minBalance, double interestRate) {
        super(accountNumber, name, age, balance, "Savings");

        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount, int pin) {

        if (balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Cannot withdraw. Minimum balance of Rs " + minBalance + " required.");
        }

        return super.withdraw(amount, pin);
    }

    public void applyInterest() {
        double interest = balance * interestRate / 100;
        balance = balance + interest;
    }
}