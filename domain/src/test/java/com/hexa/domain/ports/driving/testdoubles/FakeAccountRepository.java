package com.hexa.domain.ports.driving.testdoubles;

import com.hexa.domain.exceptions.AccountNotFoundException;
import com.hexa.domain.models.Account;
import com.hexa.domain.ports.driven.AccountRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FakeAccountRepository implements AccountRepository, TestState<Account> {

    private final Map<Integer, Account> accountMap = new ConcurrentHashMap<>();
    private final Random rand = new Random();

    @Override
    public Account findById(int id) {
        return Optional.ofNullable(accountMap.get(id)).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account save(Account account) {
        final int accountId = account.id() != null ? account.id() : rand.nextInt();
        Account saved = new Account(accountId, account.balance());
        accountMap.put(saved.id(), saved);
        return saved;
    }

    @Override
    public void init(Account... elements) {
        reset();
        accountMap.putAll(Arrays.stream(elements).collect(Collectors.toMap(account -> account.id(), account -> account)));
    }

    @Override
    public void reset() {
        accountMap.clear();
    }

    @Override
    public List<Account> getCurrentState() {
        return accountMap.values().stream().toList();
    }
}
