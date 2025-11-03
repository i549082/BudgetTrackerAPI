package com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Response;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionsResponse {
    private Long  id;
    private Long  personId;
    private TransactionType transactionType;
    private AccountType accountType;
    private String description;
    private BigDecimal amount;
}
