package com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
