package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173") // adjust if needed
public class AdminController {

    private final PersonRepository personRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public AdminController(PersonRepository personRepository,
                           MoneyTransactionRepository moneyTransactionRepository) {
        this.personRepository = personRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Person> getAllUsers() {
        return personRepository.getAllPeople();
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MoneyTransaction> getAllTransactions() {
        return moneyTransactionRepository.getAllTransactions();
    }
}
