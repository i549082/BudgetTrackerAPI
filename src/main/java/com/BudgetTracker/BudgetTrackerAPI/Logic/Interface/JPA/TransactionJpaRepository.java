package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.MoneyTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionJpaRepository extends JpaRepository<MoneyTransactionEntity, Long> {
    Optional<MoneyTransactionEntity> findByDescription(String description);
    List<MoneyTransactionEntity> findByPersonId(Long personId);

}
