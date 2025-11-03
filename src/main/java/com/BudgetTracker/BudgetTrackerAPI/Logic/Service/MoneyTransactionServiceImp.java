package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyTransactionServiceImp implements MoneyTransactionService {

    private final PersonRepository personRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public MoneyTransactionServiceImp(PersonRepository personRepository, MoneyTransactionRepository moneyTransactionRepository) {
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

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        var balance = personRepository.GetPersonBalance(userId); // note to self, big decimal is immutable

        if (transactionType == TransactionType.INCOME) {
            var newBalance = balance.add(amount);
            personRepository.UpdatePersonBalance(userId, newBalance);
        }
        if (transactionType == TransactionType.EXPENSE) {
            var newBalance = balance.subtract(amount);
            personRepository.UpdatePersonBalance(userId, newBalance);
        }

        MoneyTransaction savedTransaction = moneyTransactionRepository.SaveTransaction(userId, amount, description, transactionType, accountType);

        return savedTransaction;
    }
}
