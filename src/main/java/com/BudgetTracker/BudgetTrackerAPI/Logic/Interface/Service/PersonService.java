package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service;

import java.math.BigDecimal;

public interface PersonService {
    BigDecimal getBalance(Long userId );
    BigDecimal getTotalExpense(Long userId );
    BigDecimal getTotalIncome(Long userId );
    BigDecimal getBalanceBank(Long userId);
    BigDecimal getCashBalance(Long userId);
}
