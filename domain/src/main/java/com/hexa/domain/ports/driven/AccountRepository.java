package com.hexa.domain.ports.driven;

import com.hexa.domain.models.Account;

public interface AccountRepository  {
    Account findById(int id);
    Account save(Account account);
}
