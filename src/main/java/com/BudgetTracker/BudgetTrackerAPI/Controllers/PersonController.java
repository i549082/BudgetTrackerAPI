package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Get total balance",
            description = "Retrieves the total balance (bank + cash) for a specific user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
      return  personService.getBalance(id);
    }

    @Operation(
            summary = "Get total expenses",
            description = "Retrieves the total expenses for a specific user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expenses retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}/expense")
    public BigDecimal getExpense(@PathVariable Long id) {
        return  personService.getTotalExpense(id);
    }

    @Operation(
            summary = "Get total income",
            description = "Retrieves the total income for a specific user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Income retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}/income")
    public BigDecimal getIncome(@PathVariable Long id) {
        return  personService.getTotalIncome(id);
    }

    @Operation(
            summary = "Get bank balance",
            description = "Retrieves the bank account balance for a specific user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank balance retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}/balance/bank")
    public BigDecimal getBalanceBank(@PathVariable Long id) {
        return  personService.getBalanceBank(id);
    }

    @Operation(
            summary = "Get cash balance",
            description = "Retrieves the cash balance for a specific user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cash balance retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{id}/balance/cash")
    public BigDecimal getBalanceCash(@PathVariable Long id) {
        return  personService.getCashBalance(id);
    }
}
