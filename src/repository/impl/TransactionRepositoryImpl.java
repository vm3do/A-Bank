package repository.impl;

import model.Transaction;
import model.Account;
import repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransactionRepositoryImpl implements TransactionRepository {
    
    private List<Transaction> transactions = new ArrayList<>();
    
    @Override
    public Optional<Transaction> findById(UUID id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }
    
    @Override
    public List<Transaction> findByAccount(Account account) {
        return transactions.stream()
                .filter(transaction -> 
                    (transaction.getFromAccount() != null && transaction.getFromAccount().equals(account)) ||
                    (transaction.getToAccount() != null && transaction.getToAccount().equals(account))
                )
                .collect(Collectors.toList());
    }
    
    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
    
    @Override
    public boolean deleteById(UUID id) {
        return transactions.removeIf(transaction -> transaction.getId().equals(id));
    }
}