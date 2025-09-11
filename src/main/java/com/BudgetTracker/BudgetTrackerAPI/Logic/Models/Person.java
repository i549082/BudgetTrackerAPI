package com.BudgetTracker.BudgetTrackerAPI.Logic.Models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int  id;
    private String username;
    private String email;
    @Setter(AccessLevel.NONE)
    private String hashedPassword; // readonly for now
    private BigDecimal balance;
}
