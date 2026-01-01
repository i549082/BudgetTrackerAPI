package com.BudgetTracker.BudgetTrackerAPI.Security;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonService;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonService personRepository;

    public CustomUserDetailsService(PersonService personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.getPersonByUsername(username);
        return  new CustomUserDetails(person);
    }

}
