package com.hexa.domain.exceptions;

public class InsufficientFundsException extends RuntimeException {
    private static final String INSUFFICIENT_FUNDS_TO_PERFORM_WITHDRAWAL = "Insufficient funds to perform withdrawal.";

    public InsufficientFundsException(){
        super(INSUFFICIENT_FUNDS_TO_PERFORM_WITHDRAWAL);
    }
}
