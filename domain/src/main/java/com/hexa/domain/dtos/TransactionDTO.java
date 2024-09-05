package com.hexa.domain.dtos;

import com.hexa.domain.enums.TransactionType;

import java.time.ZonedDateTime;


public record TransactionDTO (long amount, ZonedDateTime transactionDateTime, TransactionType type){
}
