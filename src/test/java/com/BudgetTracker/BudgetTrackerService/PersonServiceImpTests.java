package com.BudgetTracker.BudgetTrackerService;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Service.PersonServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImpTests {
    @Mock
    private PersonRepository personRepository; //mocked db interface

    @InjectMocks
    private PersonServiceImp personService;

    @Test
    void getBalance_returnsBalance_whenUserExists() {
        Long userId = 1L;
        when(personRepository.existsById(userId)).thenReturn(true);
        when(personRepository.getPersonBalance(userId)).thenReturn(new BigDecimal("150.00"));

        BigDecimal result = personService.getBalance(userId);

        assertEquals(new BigDecimal("150.00"), result);
    }

    @Test
    void getBankBalance_returnsBankBalance_whenUserExists() {
        Long userId = 1L;
        when(personRepository.existsById(userId)).thenReturn(true);
        when(personRepository.getBankIncome(userId)).thenReturn(List.of(
                new BigDecimal("100.00"),
                new BigDecimal("200.00") //300
        ));
        when(personRepository.getBankExpenses(userId)).thenReturn(List.of(
                new BigDecimal("50.00"),
                new BigDecimal("50.00") //100
        ));

        BigDecimal result = personService.getBalanceBank(userId);

        assertEquals(new BigDecimal("200.00"), result);
    }

    @Test
    void getCashBalance_returnsCashBalance_whenUserExists() {
        Long userId = 1L;
        when(personRepository.existsById(userId)).thenReturn(true);
        when(personRepository.getCashIncome(userId)).thenReturn(List.of(
                new BigDecimal("100.00"),
                new BigDecimal("200.00") //300
        ));
        when(personRepository.getCashExpenses(userId)).thenReturn(List.of(
                new BigDecimal("50.00"),
                new BigDecimal("50.00") //150
        ));

        BigDecimal result = personService.getCashBalance(userId);

        assertEquals(new BigDecimal("200.00"), result);
    }

    @Test
    void getTotalExpense_returnsSumOfExpenses_whenUserExists() {
        Long userId = 1L;
        when(personRepository.existsById(userId)).thenReturn(true);
        when(personRepository.getExpenses(userId)).thenReturn(List.of(
                new BigDecimal("10"),
                new BigDecimal("20")
        ));

        BigDecimal total = personService.getTotalExpense(userId);

        assertEquals(new BigDecimal("30"), total);
    }

    @Test
    void getTotalIncome_returnsSumOfIncome_whenUserExists() {
        Long userId = 1L;
        when(personRepository.existsById(userId)).thenReturn(true);
        when(personRepository.getIncome(userId)).thenReturn(List.of(
                new BigDecimal("10"),
                new BigDecimal("20")
        ));

        BigDecimal total = personService.getTotalIncome(userId);

        assertEquals(new BigDecimal("30"), total);
    }
}
