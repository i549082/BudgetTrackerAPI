package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
      return  personService.getBalance(id);
    }

    @GetMapping("/{id}/expense")
    public BigDecimal getExpense(@PathVariable Long id) {
        return  personService.getTotalExpense(id);
    }

    @GetMapping("/{id}/income")
    public BigDecimal getIncome(@PathVariable Long id) {
        return  personService.getTotalIncome(id);
    }

    @GetMapping("/{id}/balance/bank")
    public BigDecimal getBalanceBank(@PathVariable Long id) {
        return  personService.getBalanceBank(id);
    }

    @GetMapping("/{id}/balance/cash")
    public BigDecimal getBalanceCash(@PathVariable Long id) {
        return  personService.getCashBalance(id);
    }
}
