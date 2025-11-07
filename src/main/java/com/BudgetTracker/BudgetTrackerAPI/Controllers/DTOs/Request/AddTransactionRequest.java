package com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AddTransactionRequest {

//    Long userId, TransactionType transactionType, AccountType accountType, String description, BigDecimal amount
    private Long userId;
    private AccountType accountType;
    private TransactionType transactionType;
    private String description;
    private BigDecimal amount;
}
