package com.hexa.api.config;

import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.action.command.WithdrawCommand;
import com.hexa.domain.action.command.WriteTransactionCommand;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.AccountRepository;
import com.hexa.domain.ports.driven.TransactionRepository;
import com.hexa.domain.ports.driving.AccountPort;
import com.hexa.domain.ports.driving.TransactionPort;
import com.hexa.domain.adapters.AccountAdapter;
import com.hexa.domain.adapters.TransactionAdapter;
import com.hexa.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfig {
    @Bean
    TransactionPort transactionService(UseCase<ReadTransactionByAccountIdCommand, List<TransactionDTO>> readTransactionsByAccountIdUseCase, UseCase<WriteTransactionCommand, Transaction> writeTransactionUseCase) {
        return new TransactionAdapter(readTransactionsByAccountIdUseCase, writeTransactionUseCase);
    }

    @Bean
    AccountPort accountService(
            UseCase<WithdrawCommand, Void> withdrawUseCase,
            UseCase<DepositCommand, Void> depositUseCase,
            AccountRepository jpaAccountRepository) {
        return new AccountAdapter(withdrawUseCase, depositUseCase, jpaAccountRepository);
    }

    @Bean
    UseCase<WithdrawCommand, Void> withdraw(AccountRepository jpaAccountRepository, TransactionRepository jpaTransactionRepository) {
        return new WithdrawUseCase(jpaAccountRepository, jpaTransactionRepository);
    }

    @Bean
    UseCase<DepositCommand, Void> deposit(AccountRepository jpaAccountRepository, TransactionRepository jpaTransactionRepository) {
        return new DepositUseCase(jpaAccountRepository, jpaTransactionRepository);
    }

    @Bean
    UseCase<ReadTransactionByAccountIdCommand, List<TransactionDTO>> readTransactionsByAccountIdUseCase(TransactionRepository jpaTransactionRepository) {
        return new ReadTransactionsByAccountIdUseCase(jpaTransactionRepository);
    }

    @Bean
    UseCase<WriteTransactionCommand, Transaction> writeTransactionUseCase(TransactionRepository jpaTransactionRepository) {
        return new WriteTransactionUseCase(jpaTransactionRepository);
    }
}
