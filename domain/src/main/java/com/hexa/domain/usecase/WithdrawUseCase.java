package com.hexa.domain.usecase;

import com.hexa.domain.action.command.WithdrawCommand;
import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.exceptions.InsufficientFundsException;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.AccountRepository;
import com.hexa.domain.ports.driven.TransactionRepository;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class WithdrawUseCase implements UseCase<WithdrawCommand, Void> {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    @Override
    public Void process(WithdrawCommand command) {
        final ZonedDateTime now = ZonedDateTime.now();
        final Account account = accountRepository.findById(command.accountId());
        final long currentBalance = account.balance();
        if(command.amount() > currentBalance) {
            throw new InsufficientFundsException();
        }
        final Account updatedAccount = new Account(account.id(), currentBalance - command.amount());
        accountRepository.save(updatedAccount);
        final Transaction transaction = new Transaction(null, command.amount(), updatedAccount, now, TransactionType.WITHDRAWAL);
        transactionRepository.save(transaction);
        return null;
    }
}
