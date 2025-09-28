package repository.impl;

import model.Account;
import model.Client;
import repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountRepositoryImpl implements AccountRepository {
    
    private List<Account> accounts = new ArrayList<>();
    
    @Override
    public Optional<Account> findById(UUID id) {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }
    
    @Override
    public List<Account> findByClient(Client client) {
        return accounts.stream()
                .filter(account -> account.getOwner().equals(client))
                .collect(java.util.stream.Collectors.toList());
    }
    
    @Override
    public void save(Account account) {
        accounts.add(account);
    }
    
    @Override
    public boolean deleteById(UUID id) {
        return accounts.removeIf(account -> account.getId().equals(id));
    }
}