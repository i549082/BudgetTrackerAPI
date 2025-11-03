package com.BudgetTracker.BudgetTrackerService;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BudgetTrackerServiceTests {


    @Mock
    private PersonRepository personRepository;

    @Mock
    private MoneyTransactionRepository moneyTransactionRepository;

    @InjectMocks
    private MoneyTransactionServiceImp moneyTransactionService;

    private final Long VALID_USER_ID = 1L;
    private final BigDecimal NEW_TRANSACTION = new BigDecimal("10");
    private final String DESCRIPTION = "Test transaction";

    @Test
    void addTransaction_WithValidIncome_ShouldUpdateBalanceAndSaveTransaction() {
        //arrange
        BigDecimal initialBalance = new BigDecimal("10");
        BigDecimal expectedNewBalance = new BigDecimal("20");
        MoneyTransaction expectedTransaction = new MoneyTransaction();

        when(personRepository.ExistsById(VALID_USER_ID)).thenReturn(true);
        when(personRepository.GetPersonBalance(VALID_USER_ID)).thenReturn(initialBalance);
        when(moneyTransactionRepository.SaveTransaction(VALID_USER_ID, NEW_TRANSACTION, DESCRIPTION,
                TransactionType.INCOME, AccountType.BANK)).thenReturn(expectedTransaction);

        //act
        MoneyTransaction result = moneyTransactionService.AddTransaction(VALID_USER_ID,
                TransactionType.INCOME, AccountType.CASH, DESCRIPTION, NEW_TRANSACTION);

        //assert
        verify(personRepository).UpdatePersonBalance(VALID_USER_ID, expectedNewBalance);
        verify(moneyTransactionRepository).SaveTransaction(VALID_USER_ID, NEW_TRANSACTION,
                DESCRIPTION, TransactionType.INCOME, AccountType.CASH);
        assertEquals(expectedTransaction, result);
    }
}
