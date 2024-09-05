package com.hexa.domain.ports.driving;

import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.action.command.WithdrawCommand;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.adapters.AccountAdapter;
import com.hexa.domain.adapters.TransactionAdapter;
import com.hexa.domain.ports.driving.testdoubles.FakeAccountRepository;
import com.hexa.domain.ports.driving.testdoubles.FakeTransactionRepository;
import com.hexa.domain.ports.driving.testdoubles.TestState;
import com.hexa.domain.usecase.*;

public class TestDi {

    private final static FakeAccountRepository accountRepository = new FakeAccountRepository();
    private final static FakeTransactionRepository transactionRepository = new FakeTransactionRepository();
    private final static ReadTransactionsByAccountIdUseCase readTransactionsByAccountIdUseCase = new ReadTransactionsByAccountIdUseCase(transactionRepository);
    private final static WriteTransactionUseCase writeTransactionUseCase = new WriteTransactionUseCase(transactionRepository);
    public static AccountPort accountPort(){
        return new AccountAdapter(withdrawUseCase(), depositUseCase(),accountRepository);
    }

    private static UseCase<WithdrawCommand, Void> withdrawUseCase() {
        return new WithdrawUseCase(accountRepository,transactionRepository);
    }

    private static UseCase<DepositCommand, Void> depositUseCase() {
        return new DepositUseCase(accountRepository,transactionRepository);
    }

    public static TransactionPort transactionUseCase(){
        return new TransactionAdapter(readTransactionsByAccountIdUseCase, writeTransactionUseCase);
    }

    public static TestState<Account> accountTestState(){
        return accountRepository;
    }

    public static TestState<Transaction> transactionTestState() {
        return transactionRepository;
    }
}
