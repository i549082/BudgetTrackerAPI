package com.BudgetTracker.BudgetTrackerAPI.Logic.Models;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    private int  id;
    private TransactionType transactionType;
    private String description;
    private double amount;
    private Timestamp dateCreated;
}
