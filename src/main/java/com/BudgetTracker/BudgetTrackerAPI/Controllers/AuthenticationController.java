package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.AuthenticationResponse;
import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.LoginDto;
import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.RegisterDto;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }
//@Valid what is this, google later

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }
}
