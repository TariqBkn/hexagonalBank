package com.hexa.persistence.jpaPersistence.mappers.DefaultMappers;

import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import com.hexa.persistence.jpaPersistence.mappers.AccountMapper;
import com.hexa.domain.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class DefaultAccountMapper implements AccountMapper {

    private static final String ACCOUNT_ENTITY_MUST_NOT_BE_NULL = "AccountEntity must not be null.";
    private static final String ACCOUNT_MUST_NOT_BE_NULL = "Account must not be null.";

    public Account toDomain(AccountEntity accountEntity){
        Assert.notNull(accountEntity, ACCOUNT_ENTITY_MUST_NOT_BE_NULL);
        final Account account = new Account(accountEntity.getId(), accountEntity.getBalance());

        return account;
    }

    @Override
    public AccountEntity toEntity(Account account) {
        Assert.notNull(account, ACCOUNT_MUST_NOT_BE_NULL);
        final AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId(account.id());
        accountEntity.setBalance(account.balance());

        return accountEntity;
    }
}
