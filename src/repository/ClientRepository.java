package repository;

import model.Client;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ClientRepository {
    Optional<Client> findById(UUID id);

    Optional<Client> findByEmail(String email);

    List<Client> findAll();

    void save(Client client);

    boolean deleteById(UUID id);
}