package com.hexa.persistence.jpaPersistence.repositories.adapterRepositoriesImpl;

import com.hexa.domain.exceptions.AccountNotFoundException;
import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import com.hexa.persistence.jpaPersistence.entities.TransactionEntity;
import com.hexa.persistence.jpaPersistence.mappers.TransactionMapper;
import com.hexa.persistence.jpaPersistence.repositories.JPAAccountRepository;
import com.hexa.persistence.jpaPersistence.repositories.JPATransactionRepository;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final JPATransactionRepository jpaTransactionRepository;
    private final JPAAccountRepository jpaAccountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getAllByAccountId(int clientId) {
        final AccountEntity account = jpaAccountRepository.findById(clientId).orElseThrow(AccountNotFoundException::new);
        final List<TransactionEntity> transactionEntities = jpaTransactionRepository.getAllByAccount(account);
        return transactionEntities.stream().map(transactionMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Transaction save(Transaction transaction) {
        final TransactionEntity savedTransactionEntity = jpaTransactionRepository.save(transactionMapper.ToEntity(transaction));
        return transactionMapper.toDomain(savedTransactionEntity);
    }
}
