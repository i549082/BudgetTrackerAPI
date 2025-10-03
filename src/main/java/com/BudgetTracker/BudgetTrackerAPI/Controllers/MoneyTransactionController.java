package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request.AddTransactionRequestDTO;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoneyTransactionController {

    private final MoneyTransactionService moneyTransactionService;

    public MoneyTransactionController(MoneyTransactionService moneyTransactionService) {
        this.moneyTransactionService = moneyTransactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<MoneyTransaction> createMoneyTransaction(@RequestBody AddTransactionRequestDTO transactionDTO){
        MoneyTransaction createMoneyTransaction = moneyTransactionService.AddTransaction(
                transactionDTO.getUserId(),
                transactionDTO.getTransactionType(),
                transactionDTO.getAccountType(),
                transactionDTO.getDescription(),
                transactionDTO.getAmount()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createMoneyTransaction);
    }

}
