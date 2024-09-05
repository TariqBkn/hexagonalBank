package com.hexa.persistence.jpaPersistence.mappers.DefaultMappers;

import com.hexa.persistence.jpaPersistence.entities.TransactionEntity;
import com.hexa.persistence.jpaPersistence.mappers.AccountMapper;
import com.hexa.persistence.jpaPersistence.mappers.TransactionMapper;
import com.hexa.domain.models.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class DefaultTransactionMapper implements TransactionMapper {

    private static final String TRANSACTION_ENTITY_MUST_NOT_BE_NULL = "TransactionEntity must not be null.";
    private static final String TRANSACTION_MUST_NOT_BE_NULL = "Transaction must not be null.";
    private final AccountMapper accountMapper;

    @Override
    public Transaction toDomain(TransactionEntity transactionEntity) {
        Assert.notNull(transactionEntity, TRANSACTION_ENTITY_MUST_NOT_BE_NULL);
        final Transaction transaction = new Transaction(transactionEntity.getId(), transactionEntity.getAmount(), accountMapper.toDomain(transactionEntity.getAccount()), transactionEntity.getDateTime(), transactionEntity.getType());

        return transaction;
    }

    @Override
    public TransactionEntity ToEntity(Transaction transaction) {
        Assert.notNull(transaction, TRANSACTION_MUST_NOT_BE_NULL);
        final TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setId(transaction.id());
        transactionEntity.setAccount(accountMapper.toEntity(transaction.account()));
        transactionEntity.setAmount(transaction.amount());
        transactionEntity.setDateTime(transaction.dateTime());
        transactionEntity.setType(transaction.type());

        return transactionEntity;
    }
}
