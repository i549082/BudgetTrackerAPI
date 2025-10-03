package com.BudgetTracker.BudgetTrackerService;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.MoneyTransactionService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Service.MoneyTransactionServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import  org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BudgetTrackerServiceTests {

    @InjectMocks
    private MoneyTransactionServiceImp moneyTransactionService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MoneyTransactionRepository moneyTransactionRepository;

    @Test
    void simpleTest() {
        assertEquals(2, 2);
    }
}

