package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByUsername(String username);
    Optional <PersonEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
