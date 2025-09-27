package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Account;

public interface AccountRepository {

    Optional<Account> findById(UUID id);
    List<Account> findAll();
    void save(Account account);
    boolean deleteById(UUID id);
    
}