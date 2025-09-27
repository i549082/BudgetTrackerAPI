package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyTransactionService {

    private final PersonRepository personRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public MoneyTransactionService(PersonRepository personRepository,  MoneyTransactionRepository moneyTransactionRepository) {
        this.personRepository = personRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

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
        MoneyTransaction savedTransaction = moneyTransactionRepository.SaveTransaction(userId, amount, description, transactionType, accountType);

        return savedTransaction;


    }


}
