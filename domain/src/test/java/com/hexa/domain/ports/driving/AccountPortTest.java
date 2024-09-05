package com.hexa.domain.ports.driving;

import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.dtos.AccountBalanceDTO;
import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.exceptions.InsufficientFundsException;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driving.testdoubles.TestState;
import com.hexa.domain.action.command.WithdrawCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountPortTest {

    private AccountPort accountService = TestDi.accountPort();
    private TestState<Account> accountTestState = TestDi.accountTestState();
    private TestState<Transaction> transactionTestState = TestDi.transactionTestState();

    @BeforeEach
    public void setUp(){
        accountTestState.reset();
        transactionTestState.reset();
    }

    @Test
    void testGetAccountBalanceByClientId() {
        // given
        int expectedBalance = 300;
        int clientId = 1;
        accountTestState.init(new Account(clientId, expectedBalance));
        // when
        AccountBalanceDTO actualBalance = accountService.getBalanceByAccountId(clientId);
        //then
        assertThat(actualBalance).isEqualTo(new AccountBalanceDTO(expectedBalance));
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 120,160})
    void testDepositAmountIntoAccount(int amountToDeposit) {
        // given
        final int accountId = 1;
        final int accountBalance = 200;
        final Account account = new Account(accountId, accountBalance);
        accountTestState.init(account);

        final long newBalance = account.balance() + amountToDeposit;
        final Account updatedAccount = new Account(account.id(), newBalance);

        // When
        accountService.deposit(new DepositCommand(amountToDeposit, accountId));

        // then
        final List<Account> accounts = accountTestState.getCurrentState().stream().filter(acc -> acc.id() == accountId).collect(Collectors.toList());
        final List<Transaction> transactions = transactionTestState.getCurrentState();

        final Transaction savedTransaction = new Transaction(null, transactions.get(0).amount(), transactions.get(0).account(), null, transactions.get(0).type());
        final Transaction expectedTransaction = new Transaction(null, amountToDeposit, updatedAccount, null, TransactionType.DEPOSIT);

        assertThat(accounts.size()).isEqualTo(1);
        assertThat(transactions.size()).isEqualTo(1);

        assertThat(accounts.get(0)).usingRecursiveComparison().isEqualTo(updatedAccount);
        assertThat(savedTransaction).usingRecursiveComparison().isEqualTo(expectedTransaction);
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 120,160})
    void testWithdrawAmountFromAccount_when_fundsAreSufficient(long amountToWithDraw) throws InsufficientFundsException {
        // given
        final int accountId = 1;
        final int accountBalance = 200;
        final Account account = new Account(accountId, accountBalance);
        final Account updatedAccount = new Account(account.id(), account.balance() - amountToWithDraw);
        accountTestState.init(account);

        // when
        accountService.withdraw(new WithdrawCommand(amountToWithDraw, accountId));

        // then
        final List<Account> accounts = accountTestState.getCurrentState().stream().filter(acc -> acc.id() == accountId).collect(Collectors.toList());
        final List<Transaction> transactions = transactionTestState.getCurrentState();

        final Transaction savedTransaction = new Transaction(null, transactions.get(0).amount(), transactions.get(0).account(), null, transactions.get(0).type());
        final Transaction expectedTransaction = new Transaction(null, amountToWithDraw, updatedAccount, null, TransactionType.WITHDRAWAL);

        assertThat(accounts.size()).isEqualTo(1);
        assertThat(transactions.size()).isEqualTo(1);

        assertThat(accounts.get(0)).usingRecursiveComparison().isEqualTo(updatedAccount);
        assertThat(savedTransaction).usingRecursiveComparison().isEqualTo(expectedTransaction);
    }

    @ParameterizedTest
    @ValueSource(ints = {150, 200, Integer.MAX_VALUE})
    void testWithdrawAmountFromAccount_when_fundsAreNotSufficient(long amountToWithdraw) {
        // given
        final int accountId = 1;
        final int accountBalance = 100;
        final Account account = new Account(accountId, accountBalance);
        accountTestState.init(account);


        // when
        Executable executable = () -> accountService.withdraw(new WithdrawCommand(amountToWithdraw, accountId));

        // then
        assertThrows(InsufficientFundsException.class, executable);
    }
}