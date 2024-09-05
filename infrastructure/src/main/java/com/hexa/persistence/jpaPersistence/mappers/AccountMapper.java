package com.hexa.persistence.jpaPersistence.mappers;

import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import com.hexa.domain.models.Account;

public interface AccountMapper {
    Account toDomain(AccountEntity accountEntity);
    AccountEntity toEntity(Account account);
}
