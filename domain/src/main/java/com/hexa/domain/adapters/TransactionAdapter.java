package com.hexa.domain.adapters;

import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.action.command.WriteTransactionCommand;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driving.TransactionPort;
import com.hexa.domain.usecase.ReadTransactionsByAccountIdUseCase;
import com.hexa.domain.usecase.UseCase;
import com.hexa.domain.usecase.WriteTransactionUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TransactionAdapter implements TransactionPort {
    private final UseCase<ReadTransactionByAccountIdCommand, List<TransactionDTO>> readTransactionsByAccountIdUseCase;
    private final UseCase<WriteTransactionCommand, Transaction> writeTransactionUseCase;

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(ReadTransactionByAccountIdCommand command) {
        return readTransactionsByAccountIdUseCase.process(command);
    }

    @Override
    public Transaction save(WriteTransactionCommand writeTransactionCommand) {
        return writeTransactionUseCase.process(writeTransactionCommand);
    }
}
