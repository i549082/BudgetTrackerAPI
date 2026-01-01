package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.Role;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PersonServiceImp implements com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService { // NOTE: SUBSTRACT FROM USERS BALANCE NOT BIGDECIMAL.ZERO

    private final PersonService personRepository;

    public PersonServiceImp(PersonService personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public BigDecimal getBalance(Long userId ) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        var balance = personRepository.getPersonBalance(userId);

        return balance;
    }

//    hello world x2

    @Override
    public BigDecimal getTotalExpense(Long userId ) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        List<BigDecimal> expenses = personRepository.getExpenses(userId);

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

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        BigDecimal expense = getTotalExpense(userId);


        List<BigDecimal> incomeList = personRepository.getIncome(userId);

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

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        BigDecimal totalBankBalance = BigDecimal.ZERO;

        List<BigDecimal> bankIncome = personRepository.getBankIncome(userId);
        List<BigDecimal> bankExpense = personRepository.getBankExpenses(userId);

        for (BigDecimal income: bankIncome) {
            totalBankBalance = totalBankBalance.add(income);
        }

        for (BigDecimal income: bankExpense) {
            totalBankBalance = totalBankBalance.subtract(income);
        }

        return totalBankBalance;
    }

    @Override
    public BigDecimal getCashBalance(Long userId){
        if (userId  == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!personRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        BigDecimal totalCashBalance = BigDecimal.ZERO;

        List<BigDecimal> cashIncome = personRepository.getCashIncome(userId);
        List<BigDecimal> cashExpense = personRepository.getCashExpenses(userId);

        for (BigDecimal income: cashIncome) {
            totalCashBalance = totalCashBalance.add(income);
        }

        for (BigDecimal income: cashExpense) {
            totalCashBalance = totalCashBalance.subtract(income);
        }

        return totalCashBalance;
    }

    @Override
    public List<Person> getAllPeople(){
        return  personRepository.getAllPeople();
    }

    @Override
    public void updatePersonRole(Long userId, Role role){
        personRepository.updatePersonRole(userId, role);
    }
}
