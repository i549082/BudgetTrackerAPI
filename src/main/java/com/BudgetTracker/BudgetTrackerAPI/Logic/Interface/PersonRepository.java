package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;

import java.math.BigDecimal;

public interface PersonRepository {

    public Person GetPersonById (Long id );
    public Person GetPersonByUsername ( String username );
    public Person GetPersonByEmail ( String email );
    public boolean PersonExistsByUsername ( String username );
    public boolean PersonExistsByEmail ( String email );
    public BigDecimal GetPersonBalance (Long Id );
}
