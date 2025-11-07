package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;

import java.math.BigDecimal;
import java.util.List;

public interface PersonRepository {

    Person getPersonById(Long id );
    Person getPersonByUsername(String username );
    Person getPersonByEmail(String email );
    boolean personExistsByUsername(String username );
    boolean personExistsByEmail(String email );
    boolean existsById(Long id );
    BigDecimal getPersonBalance(Long Id );
    Person updatePersonBalance(Long personId, BigDecimal newBalance);
    List<BigDecimal> getExpenses(Long id );
    List<BigDecimal> getIncome(Long id );
    List<BigDecimal> getBankIncome(Long id);
    List<BigDecimal> getBankExpenses(Long id);
    List<BigDecimal> getCashIncome(Long id);
    List<BigDecimal> getCashExpenses(Long id);
}
