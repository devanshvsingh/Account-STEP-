package com.gdb.domain;

public abstract class AbstractAccount {

    protected int accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected Integer pin;

    public AbstractAccount(int accountNumber, String name, int age,
                           double initialBalance, String accountType) {

        this.accountNumber = accountNumber;
        this.name = name;

        if (age < 18) {
            this.age = 18;
        } else {
            this.age = age;
        }

        this.accountType = accountType;

        this.balance = initialBalance;

        this.status = "Active";
        this.pin = null;
    }

    // Shared deposit method
    public boolean deposit(double amount) {

        if (status.equals("Inactive")) {
            return false;
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }

        balance = balance + amount;

        return true;
    }

    // Validate PIN
    public boolean validatePin(int pin) {

        return this.pin != null && this.pin == pin;
    }

    // Change PIN
    public boolean changePin(int oldPin, int newPin) {

        if (!validatePin(oldPin)) {
            throw new InvalidPinException("Invalid old PIN.");
        }

        if (newPin < 1000 || newPin > 9999) {
            throw new InvalidAmountException("PIN must be a 4-digit number.");
        }

        this.pin = newPin;

        return true;
    }

    // Set initial PIN
    public boolean setPin(int pin) {

        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }

        return false;
    }

    // Display account information
    public void displayAccountInfo() {

        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Status: " + status);
    }

    // Template Method
    public final void withdraw(double amount, int pin) {

        // Step 1: Validate PIN
        if (!validatePin(pin)) {
            throw new InvalidPinException("Invalid PIN.");
        }

        // Step 2: Validate account status
        if (!status.equals("Active")) {
            throw new InactiveAccountException("Account is inactive.");
        }

        // Step 3: Validate amount
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }

        // Step 4: Subclass-specific debit logic
        processDebit(amount);
    }

    // Subclasses implement their own debit rules
    protected abstract void processDebit(double amount) throws AccountException;

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
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
}