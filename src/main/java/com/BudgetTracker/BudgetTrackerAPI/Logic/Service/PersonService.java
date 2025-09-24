package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
