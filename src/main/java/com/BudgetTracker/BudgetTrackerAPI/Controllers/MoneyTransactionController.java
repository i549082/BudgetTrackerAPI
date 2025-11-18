package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request.AddTransactionRequest;
import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Response.TransactionsResponse;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MoneyTransactionController {

    private final MoneyTransactionService moneyTransactionService;

    public MoneyTransactionController(MoneyTransactionService moneyTransactionService) {
        this.moneyTransactionService = moneyTransactionService;
    }

    @Operation(
            summary = "Create a new money transaction",
            description = "Adds a new transaction for a specific user and returns transaction details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionsResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })

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
