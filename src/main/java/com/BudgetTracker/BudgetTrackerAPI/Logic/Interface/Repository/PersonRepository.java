package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;

import java.math.BigDecimal;
import java.util.List;

public interface PersonRepository {

    public Person GetPersonById (Long id );
    public Person GetPersonByUsername ( String username );
    public Person GetPersonByEmail ( String email );
    public boolean PersonExistsByUsername ( String username );
    public boolean PersonExistsByEmail ( String email );
    public boolean ExistsById ( Long id );
    public BigDecimal GetPersonBalance (Long Id );
    public Person UpdatePersonBalance(Long personId, BigDecimal newBalance);
    public List<BigDecimal> GetExpenses (Long id );
    List<BigDecimal> GetIncome (Long id );
    List<BigDecimal> GetBankIncome (Long id);
    List<BigDecimal> GetBankExpenses(Long id);
}
