package com.hexa.domain.adapters;

import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.dtos.AccountBalanceDTO;
import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.exceptions.InsufficientFundsException;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.AccountRepository;
import com.hexa.domain.ports.driven.TransactionRepository;
import com.hexa.domain.ports.driving.AccountPort;
import com.hexa.domain.action.command.WithdrawCommand;
import com.hexa.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class AccountAdapter implements AccountPort {

    private final UseCase<WithdrawCommand, Void> withdrawUseCase;
    private final UseCase<DepositCommand, Void> depositUseCase;

    private final AccountRepository accountRepository;

    @Override
    public AccountBalanceDTO getBalanceByAccountId(int id) {
        Account account = accountRepository.findById(id);
        return new AccountBalanceDTO(account.balance());
    }

    @Override
    public void deposit(DepositCommand command) {
        depositUseCase.process(command);
    }

    @Override
    public void withdraw(WithdrawCommand command) throws InsufficientFundsException {
        withdrawUseCase.process(command);
    }
}
