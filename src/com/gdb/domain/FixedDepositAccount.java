package com.gdb.domain;

public class FixedDepositAccount extends AccountEnhanced {

    private int tenureMonths;
    private double interestRate;

    public FixedDepositAccount(int accountNumber, String name, int age, double balance, int tenureMonths, double interestRate) {
        super(accountNumber, name, age, balance, "Fixed Deposit");

        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount, int pin) {
        throw new AccountException("Premature withdrawals are not permitted on Fixed Deposit accounts before maturity.");
    }

    public void calculateMaturityAmount() {
        double maturityAmount = balance * Math.pow(1 + interestRate / 100, tenureMonths / 12.0);

        System.out.println("Maturity Amount: " + maturityAmount);
    }
}