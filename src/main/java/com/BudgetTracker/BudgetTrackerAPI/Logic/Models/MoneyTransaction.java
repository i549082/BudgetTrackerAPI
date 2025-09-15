package com.BudgetTracker.BudgetTrackerAPI.Logic.Models;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MoneyTransaction {
    private Long  id;
    private Long  personId;
    private TransactionType transactionType;
    private AccountType accountType;
    private String description;
    private BigDecimal amount;
    private Timestamp dateCreated;
}
