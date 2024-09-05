package com.hexa.persistence.jpaPersistence.repositories;

import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import com.hexa.persistence.jpaPersistence.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPATransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> getAllByAccount(AccountEntity account);
}
