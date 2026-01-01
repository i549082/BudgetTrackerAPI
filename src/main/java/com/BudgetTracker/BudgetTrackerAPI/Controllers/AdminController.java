package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.Role;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173") // adjust if needed
public class AdminController {

    private final MoneyTransactionService moneyTransactionService;
    private final PersonService personService;

    public AdminController(MoneyTransactionService moneyTransactionService, PersonService personService) {
        this.moneyTransactionService = moneyTransactionService;
        this.personService = personService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Person> getAllUsers() {
        return personService.getAllPeople();
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MoneyTransaction> getAllTransactions() {
        return moneyTransactionService.getAllTransactions();
    }

    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateUserRole(
            @PathVariable Long id,
            @RequestParam Role role
    ) {
        personService.updatePersonRole(id, role);
        return ResponseEntity.ok().build();
    }
}
