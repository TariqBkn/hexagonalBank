package com.hexa.domain.action.command;

import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.models.Account;
import java.time.ZonedDateTime;

public record WriteTransactionCommand(long amount, Account account, ZonedDateTime now, TransactionType type) {
}
