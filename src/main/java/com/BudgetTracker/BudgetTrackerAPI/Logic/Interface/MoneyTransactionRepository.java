package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;

import java.math.BigDecimal;

public interface MoneyTransactionRepository {
    public MoneyTransaction GetTransactionById (Long id);
    public MoneyTransaction GetTransactionByDescription(String description);
    public void SaveTransaction(Long userId, BigDecimal amount, String description, TransactionType transactionType, AccountType accountType);

}
