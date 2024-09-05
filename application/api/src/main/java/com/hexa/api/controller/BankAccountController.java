package com.hexa.api.controller;

import com.hexa.api.wrapper.request.AccountActionRequest;
import com.hexa.domain.action.command.DepositCommand;
import com.hexa.domain.action.command.ReadTransactionByAccountIdCommand;
import com.hexa.domain.dtos.AccountBalanceDTO;
import com.hexa.api.wrapper.response.TransactionResponse;
import com.hexa.domain.dtos.TransactionDTO;
import com.hexa.domain.ports.driving.AccountPort;
import com.hexa.domain.ports.driving.TransactionPort;
import com.hexa.domain.action.command.WithdrawCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/account/{id}")
public class BankAccountController {

    private static final String WITHDRAWAL_OF_AMOUNT_SUCCESSFUL = "Withdrawal of amount '%s' successful.";
    private static final String DEPOSIT_OF_AMOUNT_SUCCESSFUL = "Deposit of amount '%s' successful.";
    private final AccountPort accountUseCase;
    private final TransactionPort transactionUseCase;

    BankAccountController(AccountPort accountUseCase, TransactionPort transactionUseCase){
        this.accountUseCase = accountUseCase;
        this.transactionUseCase = transactionUseCase;
    }

    @PatchMapping("/deposit")
    ResponseEntity<TransactionResponse> performDeposit(@PathVariable("id") int accountId, @RequestBody AccountActionRequest accountActionRequest) {
        accountUseCase.deposit(new DepositCommand(accountActionRequest.amount(), accountId));
        return ResponseEntity.ok(new TransactionResponse(accountActionRequest.amount(), DEPOSIT_OF_AMOUNT_SUCCESSFUL.formatted(accountActionRequest.amount())));
    }

    @PatchMapping("/withdrawal")
    ResponseEntity<TransactionResponse> performWithdrawal(@PathVariable("id") int accountId, @RequestBody AccountActionRequest accountActionRequest) {
        accountUseCase.withdraw(new WithdrawCommand(accountActionRequest.amount(), accountId));
        return ResponseEntity.ok(new TransactionResponse(accountActionRequest.amount(), WITHDRAWAL_OF_AMOUNT_SUCCESSFUL.formatted(accountActionRequest.amount())));
    }

    @GetMapping
    ResponseEntity<AccountBalanceDTO> checkBalance(@PathVariable("id") int accountId){
        final AccountBalanceDTO accountResponseDto = accountUseCase.getBalanceByAccountId(accountId);
        return ResponseEntity.ok(accountResponseDto);
    }

    @GetMapping("/transaction")
    ResponseEntity<List<TransactionDTO>> getTransactionsHistory(@PathVariable("id") int accountId){
        return ResponseEntity.ok(transactionUseCase.getTransactionsByAccountId(new ReadTransactionByAccountIdCommand(accountId)));
    }

}
