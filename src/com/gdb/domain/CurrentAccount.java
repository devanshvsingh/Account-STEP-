package com.gdb.domain;

public class CurrentAccount extends AccountEnhanced {

    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String name, int age, double balance, double overdraftLimit) {
        super(accountNumber, name, age, balance, "Current");

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount, int pin) {

        if (status.equals("Inactive")) {
            return false;
        }

        if (!verifyPin(pin)) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        if (amount > balance + overdraftLimit) {
            throw new InsufficientBalanceException("Insufficient balance including overdraft.");
        }

        balance = balance - amount;
        return true;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}