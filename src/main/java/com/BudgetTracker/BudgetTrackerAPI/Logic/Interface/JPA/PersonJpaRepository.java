package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByUsername(String username);
    Optional <PersonEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
