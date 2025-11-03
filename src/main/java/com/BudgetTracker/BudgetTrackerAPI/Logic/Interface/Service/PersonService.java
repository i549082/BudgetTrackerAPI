package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service;

import java.math.BigDecimal;

public interface PersonService {
    BigDecimal GetBalance (Long userId );
}
