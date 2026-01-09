package com.BudgetTracker.BudgetTrackerAPI;

import com.BudgetTracker.BudgetTrackerAPI.Controllers.DTOs.Request.AddTransactionRequest;
import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.RegisterDto;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
public class PersonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Container
    static MariaDBContainer<?> mariaDb =
            new MariaDBContainer<>("mariadb:10.11")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void registerDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDb::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDb::getUsername);
        registry.add("spring.datasource.password", mariaDb::getPassword);
    }

    @Test
    void getBalance_shouldReturnCorrectValue() throws Exception {

        RegisterDto register = new RegisterDto();
        register.setUsername("user1");
        register.setEmail("user1@test.com");
        register.setPassword("pass");

        String registerResponse = mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = objectMapper.readTree(registerResponse)
                .get("token")
                .asText();

        AddTransactionRequest income = new AddTransactionRequest();
        income.setUserId(1L);
        income.setTransactionType(TransactionType.INCOME);
        income.setAccountType(AccountType.CASH);
        income.setDescription("Job");
        income.setAmount(new BigDecimal("500"));

        mockMvc.perform(post("/api/transactions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(income)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/person/1/balance")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("500.00"));
    }
}
