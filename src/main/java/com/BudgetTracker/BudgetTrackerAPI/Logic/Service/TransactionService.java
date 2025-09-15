package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.MoneyTransactionEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.TransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final PersonRepository personRepository;


    public TransactionService(TransactionRepository transactionRepository, PersonRepository personRepository) {
        this.transactionRepository = transactionRepository;
        this.personRepository = personRepository;
    }

    public MoneyTransaction GetTransactionById (Long id) {
        var transactionEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id " + id));
        return  mapToTransactionModel(new MoneyTransactionEntity());
    }

    public MoneyTransaction GetTransactionByDescription(String description){
        var transactionEntity = transactionRepository.findByDescription(description)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with description " + description));
        return  mapToTransactionModel(new MoneyTransactionEntity());
    }

    public void AddTransaction(Long userId, BigDecimal amount, String description, TransactionType transactionType, AccountType accountType){
        var person = personRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("cannot find user with id:" + userId));


        MoneyTransactionEntity moneyTransactionEntity = new MoneyTransactionEntity();
        moneyTransactionEntity.setAmount(amount);
        moneyTransactionEntity.setDescription(description);
        moneyTransactionEntity.setTransactionType(transactionType);
        moneyTransactionEntity.setAccountType(accountType);
        moneyTransactionEntity.setPersonId(person);
        transactionRepository.save(moneyTransactionEntity);
    }

    private MoneyTransaction mapToTransactionModel ( MoneyTransactionEntity transaction ) {
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
