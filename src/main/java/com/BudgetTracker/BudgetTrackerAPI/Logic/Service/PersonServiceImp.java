package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PersonServiceImp implements PersonService { // NOTE: SUBSTRACT FROM USERS BALANCE NOT BIGDECIMAL.ZERO

    private final PersonRepository personRepository;

    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public BigDecimal GetBalance ( Long userId ) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        var balance = personRepository.GetPersonBalance(userId);

        return balance;
    }

    @Override
    public BigDecimal GetTotalExpense( Long userId ) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        List<BigDecimal> expenses = personRepository.GetExpenses(userId);

        BigDecimal totalExpenses = BigDecimal.ZERO;
        for (BigDecimal expense: expenses) {
            totalExpenses = totalExpenses.add(expense); //BigDecimal in Java is immutable - meaning methods like .add() don’t modify the object
        }

        return totalExpenses;
    }

    @Override
    public BigDecimal getTotalIncome(Long userId ) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        BigDecimal expense = GetTotalExpense(userId);


        List<BigDecimal> incomeList = personRepository.GetIncome(userId);

        BigDecimal totalIncome = BigDecimal.ZERO;
        for (BigDecimal income: incomeList) {
            totalIncome = totalIncome.add(income); //BigDecimal in Java is immutable - meaning methods like .add() don’t modify the object
        }
        return totalIncome;
    }

    @Override
    public BigDecimal getBalanceBank(Long userId){
        if (userId  == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.ExistsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        BigDecimal totalBankBalance = BigDecimal.ZERO;

        List<BigDecimal> bankIncome = personRepository.GetBankIncome(userId);
        List<BigDecimal> bankExpense = personRepository.GetBankExpenses(userId);

        for (BigDecimal income: bankIncome) {
            totalBankBalance = totalBankBalance.add(income);
        }

        for (BigDecimal income: bankExpense) {
            totalBankBalance = totalBankBalance.subtract(income);
        }

        return totalBankBalance;
    }

}
