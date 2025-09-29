package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;

import java.math.BigDecimal;

public interface MoneyTransactionRepository {
    public MoneyTransaction GetTransactionById (Long id);
    public MoneyTransaction GetTransactionByDescription(String description);
    public MoneyTransaction SaveTransaction(Long userId, BigDecimal amount, String description, TransactionType transactionType, AccountType accountType);

}
