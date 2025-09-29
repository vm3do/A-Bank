package service;

import model.Client;
import model.Account;
import model.enums.AccountType;
import repository.ClientRepository;
import repository.impl.ClientRepositoryImpl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService {
    
    private final ClientRepository clientRepository;
    
    public ClientService() {
        this.clientRepository = new ClientRepositoryImpl();
    }
    
    public Client createClient(String name, String email, String password) {
        Client client = new Client(name, email, password);
        clientRepository.save(client);
        return client;
    }
    
    public Optional<Client> findClientById(UUID clientId) {
        return clientRepository.findById(clientId);
    }
    
    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
    
    
    public boolean deleteClient(Client client) {

        for (Account account : client.getAccounts()) {
            if (account.getBalance() != 0) {
                throw new IllegalArgumentException("Cannot delete client with accounts containing funds.");
            }
        }
        
        return clientRepository.deleteById(client.getId());
    }
    
    public void addAccountToClient(Client client, Account account) {
        client.addAccount(account);
    }
    
    public void removeAccountFromClient(Client client, Account account) {
        if (account.getBalance() != 0) {
            throw new IllegalArgumentException("Cannot remove account with non-zero balance.");
        }
        
        client.getAccounts().remove(account);
    }
    
    public List<Account> getClientAccounts(Client client) {
        return client.getAccounts();
    }
    
    public int getClientAccountCount(Client client) {
        return client.getAccounts().size();
    }
    
    public double getClientTotalBalance(Client client) {
        return client.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }
    
    public boolean hasAccountOfType(Client client, AccountType accountType) {
        return client.getAccounts().stream()
                .anyMatch(account -> account.getAccountType() == accountType);
    }
    
    public void validateClientData(Client client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty.");
        }
        
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Client email cannot be empty.");
        }
        
        if (client.getPassword() == null || client.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Client password cannot be empty.");
        }
    }
}