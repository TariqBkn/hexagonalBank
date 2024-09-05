package com.hexa.domain.ports.driving.testdoubles;

import com.hexa.domain.models.Account;
import com.hexa.domain.models.Transaction;
import com.hexa.domain.ports.driven.TransactionRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FakeTransactionRepository implements TransactionRepository, TestState<Transaction> {


    private final Map<Integer, Transaction> transactionMap = new ConcurrentHashMap<>();
    private final Random rand = new Random();


    @Override
    public List<Transaction> getAllByAccountId(int id) {
        return transactionMap.values().stream().filter(transaction -> id==transaction.account().id()).toList();
    }

    @Override
    public Transaction save(Transaction transaction) {
        int transactionId = transaction.id() != null ? transaction.id() : rand.nextInt();
        Transaction saved = new Transaction(transactionId, transaction.amount(), transaction.account(), transaction.dateTime(), transaction.type());
        transactionMap.put(saved.id(), saved);
        return saved;
    }

    @Override
    public void init(Transaction... elements) {
        reset();
        transactionMap.putAll(Arrays.stream(elements).collect(Collectors.toMap(transaction -> transaction.id(), transaction -> transaction)));
    }

    @Override
    public void reset() {
        transactionMap.clear();
    }

    @Override
    public List<Transaction> getCurrentState() {
        return transactionMap.values().stream().toList();
    }
}
