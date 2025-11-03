package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request.AddTransactionRequest;
import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Response.TransactionsResponse;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MoneyTransactionController {

    private final MoneyTransactionService moneyTransactionService;

    public MoneyTransactionController(MoneyTransactionService moneyTransactionService) {
        this.moneyTransactionService = moneyTransactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionsResponse> createMoneyTransaction(@RequestBody AddTransactionRequest transactionDTO){
        MoneyTransaction createMoneyTransaction = moneyTransactionService.AddTransaction(
                transactionDTO.getUserId(),
                transactionDTO.getTransactionType(),
                transactionDTO.getAccountType(),
                transactionDTO.getDescription(),
                transactionDTO.getAmount()
        );

        TransactionsResponse transactionsResponse = new TransactionsResponse();
        transactionsResponse.setId(createMoneyTransaction.getId());
        transactionsResponse.setPersonId(createMoneyTransaction.getPersonId());
        transactionsResponse.setTransactionType(createMoneyTransaction.getTransactionType());
        transactionsResponse.setAccountType(createMoneyTransaction.getAccountType());
        transactionsResponse.setDescription(createMoneyTransaction.getDescription());
        transactionsResponse.setAmount(createMoneyTransaction.getAmount());

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsResponse);
    }
}
