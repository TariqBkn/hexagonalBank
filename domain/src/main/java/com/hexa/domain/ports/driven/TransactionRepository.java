package com.hexa.domain.ports.driven;

import com.hexa.domain.models.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAllByAccountId(int id);
    Transaction save(Transaction transaction);
}
