package com.hexa.domain.ports.driving;

import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.action.command.WriteTransactionCommand;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.enums.TransactionType;
import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driving.testdoubles.TestState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionPortTest {
    private TransactionPort transactionService = TestDi.transactionUseCase();
    private TestState<Transaction> transactionTestState = TestDi.transactionTestState();

    @BeforeEach
    public void setUp(){
        transactionTestState.reset();
    }
    @Test
    public void testSave() {
        // given
        long amount = 100;
        int accountId = 1;
        int accountBalance = 200;
        final Account account = new Account(accountId, accountBalance);
        final ZonedDateTime now = ZonedDateTime.now();
        TransactionType type = TransactionType.DEPOSIT;
        Transaction expectedTransaction = new Transaction(null, amount, account, now, type);

        // when
        Transaction savedTransaction = transactionService.save(new WriteTransactionCommand(amount, account, now, type));

        // then
        Transaction adaptedSavedTransaction  = new Transaction(null, savedTransaction.amount(), savedTransaction.account(), savedTransaction.dateTime(), savedTransaction.type());
        assertThat(expectedTransaction).usingRecursiveComparison().isEqualTo(adaptedSavedTransaction);
    }
    @Test
    public void testGetTransactionsByClientId() {
        // given
            // account
        final int accountId = 1;
        final int accountBalance = 200;
        final Account account = new Account(accountId, accountBalance);
            // transaction entities
        int transaction01Id = 1;
        long amount01 = 100;
        final Transaction transaction01 = new Transaction(transaction01Id, amount01, account, ZonedDateTime.now(), TransactionType.DEPOSIT);

        int transaction02Id = 2;
        long amount02 = 20;
        final Transaction transaction02 = new Transaction(transaction02Id, amount02, account, ZonedDateTime.now(), TransactionType.WITHDRAWAL);
        transactionTestState.init(transaction01, transaction02);

          // expected transaction DTOs
        final TransactionDTO expectedTransactionDTO1 = new TransactionDTO(transaction01.amount(), transaction01.dateTime(), transaction01.type());
        final TransactionDTO expectedTransactionDTO2 = new TransactionDTO(transaction02.amount(), transaction02.dateTime(), transaction02.type());

        final List<TransactionDTO> expectedTransactionDTOs = List.of(expectedTransactionDTO1, expectedTransactionDTO2);

        // when
        final List<TransactionDTO> actualTransactionDTOs = transactionService.getTransactionsByAccountId(new ReadTransactionByAccountIdCommand(accountId));

        // then
        assertThat(actualTransactionDTOs).usingRecursiveFieldByFieldElementComparator()
                    .isEqualTo(expectedTransactionDTOs);
    }
}