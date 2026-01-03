package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface MoneyTransactionService {
    MoneyTransaction AddTransaction(Long userId, TransactionType transactionType, AccountType accountType, String description, BigDecimal amount );
    List<MoneyTransaction> getAllTransactions();
    List<MoneyTransaction> getTransactionsById(Long id);
    MoneyTransaction deleteTransaction(Long id);
}
