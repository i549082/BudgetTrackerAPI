package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service;

import java.math.BigDecimal;

public interface PersonService {
    BigDecimal GetBalance (Long userId );
    BigDecimal GetTotalExpense( Long userId );
    BigDecimal getTotalIncome(Long userId );
    BigDecimal getBalanceBank(Long userId);
}
