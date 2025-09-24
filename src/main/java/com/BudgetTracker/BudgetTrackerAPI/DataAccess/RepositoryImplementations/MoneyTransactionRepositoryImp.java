package com.BudgetTracker.BudgetTrackerAPI.DataAccess.RepositoryImplementations;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.MoneyTransactionEntity;
import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.TransactionJpaRepository;
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
    public void SaveTransaction(Long userId, BigDecimal amount, String description, TransactionType transactionType, AccountType accountType){
        PersonEntity person = personJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("cannot find user with id:" + userId));

        MoneyTransactionEntity moneyTransactionEntity = new MoneyTransactionEntity();
        moneyTransactionEntity.setAmount(amount);
        moneyTransactionEntity.setDescription(description);
        moneyTransactionEntity.setTransactionType(transactionType);
        moneyTransactionEntity.setAccountType(accountType);
        moneyTransactionEntity.setPersonId(person);
        transactionJpaRepository.save(moneyTransactionEntity);
    }

    private MoneyTransaction MapToTransactionModel(MoneyTransactionEntity transaction ) {
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setId( transaction.getId() );
        moneyTransaction.setTransactionType( transaction.getTransactionType() );
        moneyTransaction.setAccountType( transaction.getAccountType() );
        moneyTransaction.setDescription( transaction.getDescription() );
        moneyTransaction.setAmount( transaction.getAmount() );
        moneyTransaction.setDateCreated( transaction.getDateCreated() );
        return moneyTransaction;
    }
}
