package repository;
import model.Client;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Optional <Client> findById(UUID id);
    Optional <Client> findByEmail(String email);
    void save(Client client);
    boolean deleteById(UUID id);
}