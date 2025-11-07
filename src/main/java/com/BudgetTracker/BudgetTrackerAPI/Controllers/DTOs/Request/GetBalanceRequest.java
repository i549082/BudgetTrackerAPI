package com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBalanceRequest {
    private Long userId;
}
