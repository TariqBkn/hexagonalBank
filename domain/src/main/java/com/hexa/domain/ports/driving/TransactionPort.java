package com.hexa.domain.ports.driving;

import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.action.command.WriteTransactionCommand;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionPort {
    Transaction save(WriteTransactionCommand writeTransactionCommand);

    List<TransactionDTO> getTransactionsByAccountId(ReadTransactionByAccountIdCommand command);
}
