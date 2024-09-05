package com.hexa.domain.usecase;

import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.ports.driven.TransactionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReadTransactionsByAccountIdUseCase implements UseCase<ReadTransactionByAccountIdCommand, List<TransactionDTO>> {
    private final TransactionRepository transactionRepository;
    @Override
    public List<TransactionDTO> process(ReadTransactionByAccountIdCommand command) {
        return transactionRepository.getAllByAccountId(command.accountId()).stream().map(t -> new TransactionDTO(t.amount(), t.dateTime(), t.type())).collect(Collectors.toList());
    }
}
