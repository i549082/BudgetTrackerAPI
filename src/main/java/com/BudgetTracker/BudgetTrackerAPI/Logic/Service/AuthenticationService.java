package com.BudgetTracker.BudgetTrackerAPI.Logic.Service;

import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.LoginDto;
import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.RegisterDto;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.Role;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.DTOs.AuthenticationResponse;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import com.BudgetTracker.BudgetTrackerAPI.Security.JWT.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationService(PersonRepository personRepository, PasswordEncoder passwordEncoder, JwtService jwtService,  AuthenticationManager authenticationManager) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterDto request){
       Person person = new Person();
       person.setUsername(request.getUsername());
       person.setEmail(request.getEmail());
       person.setHashedPassword(passwordEncoder.encode(request.getPassword()));
       person.setBalance(BigDecimal.ZERO);
       person.setRole(Role.USER);

       person = personRepository.savePerson(person);
       String token = jwtService.generateToken(person);

       return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate (LoginDto request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Person person = personRepository.getPersonByUsername(request.getUsername());
        String token = jwtService.generateToken(person);

        return new AuthenticationResponse(token);
    }

}
