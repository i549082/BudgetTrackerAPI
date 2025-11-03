package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
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
      return  personService.GetBalance(id);
    }
}
