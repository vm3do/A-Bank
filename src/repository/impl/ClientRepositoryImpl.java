package repository.impl;

import model.Client;
import repository.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {
    
    private List<Client> clients = new ArrayList<>();
    
    @Override
    public Optional<Client> findById(UUID id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public Optional<Client> findByEmail(String email) {
        return clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();
    }
    
    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }
    
    @Override
    public void save(Client client) {
        clients.add(client);
    }
    
    @Override
    public boolean deleteById(UUID id) {
        return clients.removeIf(client -> client.getId().equals(id));
    }
}