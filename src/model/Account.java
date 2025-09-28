package model;

import java.util.UUID;

import model.enums.AccountType;

public class Account {

    private UUID id;
    private  double balance;
    private AccountType accountType;
    private Client owner;

    public Account(double initialBalance, AccountType accountType, Client owner) {
        this.id = UUID.randomUUID();
        this.balance = initialBalance;
        this.accountType = accountType;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Client getOwner() {
        return owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
}