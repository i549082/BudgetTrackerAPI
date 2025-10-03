package com.BudgetTracker.BudgetTrackerAPI.DataAccess.RepositoryImplementations;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA.PersonJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PersonRepositoryImp implements PersonRepository { // TO DO: Add Exception Handling

    private final PersonJpaRepository personJpaRepository;

    public PersonRepositoryImp(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Person GetPersonById (Long id ) {
        PersonEntity personEntity = personJpaRepository.findById( id )
               .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        return mapToPersonModel( personEntity );

    }

    @Override
    public Person GetPersonByUsername ( String username ){
        PersonEntity personEntity =  personJpaRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username " + username));
            return mapToPersonModel( personEntity );
    }

    @Override
    public Person GetPersonByEmail ( String email ){
        PersonEntity personEntity = personJpaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return mapToPersonModel( personEntity);
    }

    @Override
    public boolean ExistsById ( Long id){
        return personJpaRepository.existsById( id );
    }

    @Override
    public boolean PersonExistsByUsername ( String username ){
        return personJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean PersonExistsByEmail ( String email ){
        return personJpaRepository.existsByEmail(email);
    }

    @Override
    public BigDecimal GetPersonBalance (Long Id ){
        var person = GetPersonById( Id );
        var balance = person.getBalance();
        return balance;
    }

    @Override
    public void UpdatePersonBalance(Long personId, BigDecimal newBalance) {
        PersonEntity personEntity = personJpaRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        personEntity.setBalance(newBalance);
        PersonEntity updatedEntity = personJpaRepository.save(personEntity);
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
