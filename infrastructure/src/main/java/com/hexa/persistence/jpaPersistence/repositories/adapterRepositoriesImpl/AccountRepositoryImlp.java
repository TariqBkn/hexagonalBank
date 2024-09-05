package com.hexa.persistence.jpaPersistence.repositories.adapterRepositoriesImpl;

import com.hexa.domain.exceptions.AccountNotFoundException;
import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import com.hexa.persistence.jpaPersistence.mappers.AccountMapper;
import com.hexa.persistence.jpaPersistence.repositories.JPAAccountRepository;
import com.hexa.domain.models.Account;
import com.hexa.domain.ports.driven.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImlp implements AccountRepository {

    private final JPAAccountRepository jpaAccountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account findById(int id) {
        final AccountEntity accountEntity = jpaAccountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        return accountMapper.toDomain(accountEntity);
    }

    @Override
    public Account save(Account account) {
        final AccountEntity accountEntity = jpaAccountRepository.save(accountMapper.toEntity(account));
        return accountMapper.toDomain(accountEntity);
    }
}
