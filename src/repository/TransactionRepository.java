package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Transaction;
import model.Account;

public interface TransactionRepository {
    Optional<Transaction> findById(UUID id);
    List<Transaction> findAll();
    List<Transaction> findByAccount(Account account);
    void save(Transaction transaction);
    boolean deleteById(UUID id);
}