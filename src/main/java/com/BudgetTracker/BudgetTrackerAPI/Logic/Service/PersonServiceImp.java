package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public BigDecimal GetBalance ( Long userId ) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        var balance = personRepository.GetPersonBalance(userId);

        return balance;
    }
}
