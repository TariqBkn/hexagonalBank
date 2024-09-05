package com.hexa.persistence.jpaPersistence.mappers;


import com.hexa.persistence.jpaPersistence.entities.TransactionEntity;
import com.hexa.domain.models.Transaction;

public interface TransactionMapper {
    Transaction toDomain(TransactionEntity transactionEntity);
    TransactionEntity ToEntity(Transaction transaction);
}
