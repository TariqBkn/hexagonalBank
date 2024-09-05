package com.hexa.domain.usecase;

import com.hexa.domain.action.command.WriteTransactionCommand;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WriteTransactionUseCase implements UseCase<WriteTransactionCommand, Transaction> {
    private final TransactionRepository transactionRepository;
    @Override
    public Transaction process(WriteTransactionCommand command) {
        final Transaction transaction = new Transaction(null, command.amount(), command.account(), command.now(), command.type());
        return transactionRepository.save(transaction);
    }
}
