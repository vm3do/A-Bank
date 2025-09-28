package model;

import java.time.LocalDateTime;
import java.util.UUID;
import model.enums.TransactionType;

public class Transaction {
    
    private UUID id;
    private double amount;
    private TransactionType type;
    private LocalDateTime date;
    private String description;
    private Account fromAccount;
    private Account toAccount;
    
    public Transaction(double amount, TransactionType type, String description, Account fromAccount, Account toAccount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
        this.description = description;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
    
    public UUID getId() {
        return id;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Account getFromAccount() {
        return fromAccount;
    }
    
    public Account getToAccount() {
        return toAccount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setType(TransactionType type) {
        this.type = type;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }
    
    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }
}