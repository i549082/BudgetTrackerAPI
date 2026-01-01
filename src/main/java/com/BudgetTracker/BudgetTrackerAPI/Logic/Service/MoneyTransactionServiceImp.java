package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MoneyTransactionServiceImp implements MoneyTransactionService {

    private final PersonService personRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public MoneyTransactionServiceImp(PersonService personRepository, MoneyTransactionRepository moneyTransactionRepository) {
        this.personRepository = personRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    @Override
    public MoneyTransaction AddTransaction(Long userId, TransactionType transactionType, AccountType accountType, String description, BigDecimal amount ) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        var balance = personRepository.getPersonBalance(userId); // note to self, big decimal is immutable

        if (transactionType == TransactionType.INCOME) {
            var newBalance = balance.add(amount);
            personRepository.updatePersonBalance(userId, newBalance);
        }
        if (transactionType == TransactionType.EXPENSE) {
            var newBalance = balance.subtract(amount);
            personRepository.updatePersonBalance(userId, newBalance);
        }

        MoneyTransaction savedTransaction = moneyTransactionRepository.SaveTransaction(userId, amount, description, transactionType, accountType);

        return savedTransaction;
    }

    @Override
    public List<MoneyTransaction> getAllTransactions(){
       return moneyTransactionRepository.getAllTransactions();
    }
}
