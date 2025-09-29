package service;

import model.Account;
import model.Client;
import model.Transaction;
import model.enums.AccountType;
import model.enums.TransactionType;
import repository.AccountRepository;
import repository.TransactionRepository;
import repository.impl.AccountRepositoryImpl;
import repository.impl.TransactionRepositoryImpl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountService {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    public AccountService() {
        this.accountRepository = new AccountRepositoryImpl();
        this.transactionRepository = new TransactionRepositoryImpl();
    }
    
    public Account createAccount(double initialBalance, AccountType accountType, Client owner) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        
        Account account = new Account(initialBalance, accountType, owner);
        accountRepository.save(account);
        return account;
    }
    
    public Optional<Account> findAccountById(UUID accountId) {
        return accountRepository.findById(accountId);
    }
    
    public List<Account> findAccountsByClient(Client client) {
        return accountRepository.findByClient(client);
    }
    
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
    
    public void deposit(Account account, double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT, description, null, account);
        transactionRepository.save(transaction);
    }
    
    public void withdraw(Account account, double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        
        Transaction transaction = new Transaction(amount, TransactionType.WITHDRAWAL, description, account, null);
        transactionRepository.save(transaction);
    }
    
    public void transfer(Account fromAccount, Account toAccount, double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        
        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }
        
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        
        Transaction transaction = new Transaction(amount, TransactionType.TRANSFER, description, fromAccount, toAccount);
        transactionRepository.save(transaction);
    }
    
    public List<Transaction> getAccountTransactions(Account account) {
        return transactionRepository.findByAccount(account);
    }
    
    public boolean deleteAccount(Account account) {
        if (account.getBalance() != 0) {
            throw new IllegalArgumentException("Cannot delete account with non-zero balance.");
        }
        
        return accountRepository.deleteById(account.getId());
    }
}