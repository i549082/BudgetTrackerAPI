package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.Role;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;

import java.math.BigDecimal;
import java.util.List;

public interface PersonService {
    BigDecimal getBalance(Long userId );
    BigDecimal getTotalExpense(Long userId );
    BigDecimal getTotalIncome(Long userId );
    BigDecimal getBalanceBank(Long userId);
    BigDecimal getCashBalance(Long userId);
    List<Person> getAllPeople();
    void updatePersonRole(Long userId, Role role);
}
