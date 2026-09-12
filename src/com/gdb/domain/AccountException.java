package com.gdb.domain;

public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}

class InvalidAmountException extends AccountException {

    public InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends AccountException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class MinimumBalanceViolationException extends AccountException {

    public MinimumBalanceViolationException(String message) {
        super(message);
    }
}

class InactiveAccountException extends AccountException {

    public InactiveAccountException(String message) {
        super(message);
    }
}

class InvalidPinException extends AccountException {

    public InvalidPinException(String message) {
        super(message);
    }
}