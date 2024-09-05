package com.hexa.domain.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public static final String ACCOUNT_NOT_FOUND_WITH_PROVIDED_ID = "Account not found with provided ID.";

    public AccountNotFoundException(){
        super(ACCOUNT_NOT_FOUND_WITH_PROVIDED_ID);
    }
}
