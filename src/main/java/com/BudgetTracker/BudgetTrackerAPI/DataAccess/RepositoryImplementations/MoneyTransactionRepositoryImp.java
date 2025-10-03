package com.BudgetTracker.BudgetTrackerAPI.DataAccess.RepositoryImplementations;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.MoneyTransactionEntity;
import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA.PersonJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA.TransactionJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyTransactionRepositoryImp implements MoneyTransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final PersonJpaRepository personJpaRepository;

    public MoneyTransactionRepositoryImp(TransactionJpaRepository transactionJpaRepository, PersonJpaRepository personJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public MoneyTransaction GetTransactionById (Long id) {
        MoneyTransactionEntity transactionEntity = transactionJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id " + id));
        return  MapToTransactionModel(transactionEntity);
    }

    @Override
    public MoneyTransaction GetTransactionByDescription(String description){
        MoneyTransactionEntity transactionEntity = transactionJpaRepository.findByDescription(description)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with description " + description));
        return  MapToTransactionModel(transactionEntity);
    }

    @Override
    public MoneyTransaction SaveTransaction(Long userId, BigDecimal amount, String description, TransactionType transactionType, AccountType accountType) {
        PersonEntity person = personJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("cannot find user with id:" + userId));

        MoneyTransactionEntity moneyTransactionEntity = MoneyTransactionEntity.builder()
                .personId(person)
                .amount(amount)
                .transactionType(transactionType)
                .accountType(accountType)
                .description(description)
                .amount(amount)
                .build();

        MoneyTransactionEntity savedEntity = transactionJpaRepository.save(moneyTransactionEntity);

        return MapToTransactionModel(savedEntity);
    }

    private MoneyTransaction MapToTransactionModel(MoneyTransactionEntity transaction ) {
        return MoneyTransaction.builder()
                .id(transaction.getId())                    // Auto-generated ID
                .personId(transaction.getPersonId().getId()) // Extract ID from PersonEntity
                .transactionType(transaction.getTransactionType())
                .accountType(transaction.getAccountType())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .dateCreated(transaction.getDateCreated())  // Auto-generated timestamp
                .build();
    }
}
