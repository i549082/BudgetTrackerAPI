package com.BudgetTracker.BudgetTrackerAPI.DataAccess.RepositoryImplementations;

import com.BudgetTracker.BudgetTrackerAPI.DataAccess.Entities.PersonEntity;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.AccountType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Enum.TransactionType;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA.TransactionJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.MoneyTransactionRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.Repository.PersonRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Interface.JPA.PersonJpaRepository;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.MoneyTransaction;
import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonRepositoryImp implements PersonRepository { // TO DO: Add Exception Handling

    private final PersonJpaRepository personJpaRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public PersonRepositoryImp(PersonJpaRepository personJpaRepository,  MoneyTransactionRepository moneyTransactionRepository) {
        this.personJpaRepository = personJpaRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    @Override
    public Person GetPersonById (Long id ) {
        PersonEntity personEntity = personJpaRepository.findById( id )
               .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        return mapToPersonModel( personEntity );
    }

    @Override
    public Person GetPersonByUsername ( String username ){
        PersonEntity personEntity =  personJpaRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username " + username));
            return mapToPersonModel( personEntity );
    }

    @Override
    public Person GetPersonByEmail ( String email ){
        PersonEntity personEntity = personJpaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return mapToPersonModel( personEntity);
    }

    @Override
    public boolean ExistsById ( Long id){
        return personJpaRepository.existsById( id );
    }

    @Override
    public boolean PersonExistsByUsername ( String username ){
        return personJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean PersonExistsByEmail ( String email ){
        return personJpaRepository.existsByEmail(email);
    }

    @Override
    public BigDecimal GetPersonBalance (Long id ){
        var person = GetPersonById( id );
        var balance = person.getBalance();
        return balance;
    }

    @Override
    public List<BigDecimal> GetExpenses (Long id ){
        List<MoneyTransaction> personTransactions = moneyTransactionRepository.GetTransactionsById(id);
        List<BigDecimal> expenses = new ArrayList<>();
        for (MoneyTransaction  transaction : personTransactions) {
            if(transaction.getTransactionType() == TransactionType.EXPENSE) {
                expenses.add(transaction.getAmount());
            }
        }
        return expenses;
    }

    @Override
    public List<BigDecimal> GetIncome (Long id ){
        List<MoneyTransaction> personTransactions = moneyTransactionRepository.GetTransactionsById(id);
        List<BigDecimal> incomeList = new ArrayList<>();
        for (MoneyTransaction  transaction : personTransactions) {
            if(transaction.getTransactionType() == TransactionType.INCOME) {
                incomeList.add(transaction.getAmount());
            }
        }
        return incomeList;
    }

    @Override
    public List<BigDecimal> GetBankIncome (Long id){
        List<MoneyTransaction> personTransactions = moneyTransactionRepository.GetTransactionsById(id);
        List<BigDecimal> bankIncomeList = new ArrayList<>();
        for (MoneyTransaction  transaction : personTransactions) {
            if(transaction.getTransactionType() == TransactionType.INCOME && transaction.getAccountType() == AccountType.BANK) {
                bankIncomeList.add(transaction.getAmount());
            }
        }
        return bankIncomeList;
    }

    @Override
    public List<BigDecimal> GetBankExpenses (Long id){
        List<MoneyTransaction> personTransactions = moneyTransactionRepository.GetTransactionsById(id);
        List<BigDecimal> bankExpensesList = new ArrayList<>();
        for (MoneyTransaction  transaction : personTransactions) {
            if(transaction.getTransactionType() == TransactionType.EXPENSE &&  transaction.getAccountType() == AccountType.BANK)  {
                bankExpensesList.add(transaction.getAmount());
            }
        }
        return bankExpensesList;
    }

    @Override
    public Person UpdatePersonBalance(Long personId, BigDecimal newBalance) {
        PersonEntity personEntity = personJpaRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        personEntity.setBalance(newBalance);
        PersonEntity updatedEntity = personJpaRepository.save(personEntity);
        return mapToPersonModel(updatedEntity);
    }

    private Person mapToPersonModel ( PersonEntity personEntity ){
        Person person = new Person();
        person.setId( personEntity.getId() );
        person.setUsername( personEntity.getUsername() );
        person.setEmail( personEntity.getEmail() );
        person.setBalance( personEntity.getBalance() );
        return person;
    }
}
