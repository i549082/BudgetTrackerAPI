package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PersonService { // TO DO: Add Exception Handling

    private final PersonRepository personRepository;

    public PersonService ( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    public Person GetPersonById (Long id ) {
        PersonEntity personEntity = personRepository.findById( id )
               .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        return mapToPersonModel( personEntity );

    }

    public Person GetPersonByUsername ( String username ){
        PersonEntity personEntity =  personRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username " + username));
            return mapToPersonModel( personEntity );
    }

    public Person GetPersonByEmail ( String email ){
        PersonEntity personEntity = personRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return mapToPersonModel( personEntity);
    }

    public boolean PersonExistsByUsername ( String username ){
        return personRepository.existsByUsername(username);
    }

    public boolean PersonExistsByEmail ( String email ){
        return personRepository.existsByEmail(email);
    }

    public BigDecimal GetPersonBalance (Long Id ){
        var person = GetPersonById( Id );
        var balance = person.getBalance();
        return balance;
    }

    private Person mapToPersonModel ( PersonEntity personEntity ){
        Person person = new Person();
        person.setId( personEntity.getId() );
        person.setUsername( personEntity.getUsername() );
        person.setEmail( personEntity.getEmail() );
        person.setBalance( personEntity.getBalance() );
        return person;
    }
}
