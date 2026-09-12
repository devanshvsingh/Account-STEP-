package com.gdb.domain;

public class AccountEnhanced {

    int accountNumber;
    String name;
    int age;
    double balance;
    String accountType;
    String status;
    Integer pin;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;

        if (age < 18) {
            this.age = 18;
        } else {
            this.age = age;
        }

        if (accountType.equals("Savings") || accountType.equals("Current")) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
        }

        if (this.accountType.equals("Savings")) {
            if (initialBalance < 500) {
                this.balance = 500;
            } else {
                this.balance = initialBalance;
            }
        } else {
            if (initialBalance < 1000) {
                this.balance = 1000;
            } else {
                this.balance = initialBalance;
            }
        }

        this.status = "Active";
        this.pin = null;
    }

    public boolean deposit(double amount) {
        if (status.equals("Inactive")) {
            return false;
        }

        balance = balance + amount;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if (status.equals("Inactive")) {
            return false;
        }

        if (!verifyPin(pin)) {
            return false;
        }

        double minimumBalance;

        if (accountType.equals("Savings")) {
            minimumBalance = 500;
        } else {
            minimumBalance = 1000;
        }

        if (balance - amount < minimumBalance) {
            return false;
        }

        balance = balance - amount;
        return true;
    }

    public boolean closeAccount() {
        if (status.equals("Inactive")) {
            return false;
        }

        status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if (status.equals("Active")) {
            return false;
        }

        status = "Active";
        return true;
    }

    public boolean setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }

        return false;
    }

    public boolean verifyPin(int pin) {
        if (this.pin != null && this.pin == pin) {
            return true;
        }

        return false;
    }

    public boolean hasPin() {
        if (pin != null) {
            return true;
        }

        return false;
    }
}