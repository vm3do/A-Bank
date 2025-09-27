package repository;

import java.util.List;
import java.util.Optional;

import model.Transaction;

public interface TransactionRepository {
    Optional<Transaction> findById(String id);
    List<Transaction> findAll();
    void save(Transaction transaction);
    boolean deleteById(String id);
}