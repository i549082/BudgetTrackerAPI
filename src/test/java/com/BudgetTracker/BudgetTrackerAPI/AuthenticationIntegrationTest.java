package com.BudgetTracker.BudgetTrackerAPI;

import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.RegisterDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
public class AuthenticationIntegrationTest {

//    mvn clean verify

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
    void registerUser_shouldReturnJwtToken() throws Exception {

        RegisterDto dto = new RegisterDto();
        dto.setUsername("alex");
        dto.setEmail("alex@test.com");
        dto.setPassword("password123");

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
