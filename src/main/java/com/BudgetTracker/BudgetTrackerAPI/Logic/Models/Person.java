package com.BudgetTracker.BudgetTrackerAPI.Logic.Models;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.Role;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long  id;
    private String username;
    private String email;
    private String hashedPassword; // readonly for now
    private BigDecimal balance;
    private Role role;
}
