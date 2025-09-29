package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;

import java.math.BigDecimal;

public interface MoneyTransactionService {
    MoneyTransaction AddTransaction(Long userId, TransactionType transactionType, AccountType accountType, String description, BigDecimal amount );
}
