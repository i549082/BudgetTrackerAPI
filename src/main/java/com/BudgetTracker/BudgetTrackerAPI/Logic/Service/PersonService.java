package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PersonService {


    private final PersonRepository personRepository;

    public PersonService ( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    public PersonEntity GetPersonById (Long id ) {
       return personRepository.findById( id )
               .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    public Optional<PersonEntity> GetPersonByUsername ( String username ){
        return personRepository.findByUsername(username);
    }

    public Optional<PersonEntity> GetPersonByEmail ( String email ){
        return personRepository.findByEmail(email);
    }

    public boolean PersonExistsByUsername ( String username ){
        return personRepository.existsByUsername(username);
    }

    public boolean PersonExistsByEmail ( String email ){
        return personRepository.existsByUsername(email);
    }

    public BigDecimal GetPersonBalance (Long Id ){
        var person = GetPersonById( Id );
        var balance = person.getBalance();
        return balance;
    }
}
