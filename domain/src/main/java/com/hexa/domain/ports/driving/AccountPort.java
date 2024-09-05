package com.hexa.domain.ports.driving;

import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.dtos.AccountBalanceDTO;
import com.hexa.domain.exceptions.InsufficientFundsException;
import com.hexa.domain.action.command.WithdrawCommand;

public interface AccountPort {
    AccountBalanceDTO getBalanceByAccountId(int id);
    void deposit(DepositCommand accountAction);
    void withdraw(WithdrawCommand accountAction) throws InsufficientFundsException;
}
