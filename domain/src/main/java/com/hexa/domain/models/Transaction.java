package com.hexa.domain.models;

import com.hexa.domain.enums.TransactionType;

import java.time.ZonedDateTime;

public record Transaction(Integer id, long amount, Account account, ZonedDateTime dateTime, TransactionType type){
}
