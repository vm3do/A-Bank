package repository;

import model.Manager;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    Optional<Manager> findById(UUID id);
    Optional<Manager> findByEmail(String email);
    List<Manager> findAll();
    void save(Manager manager);
    boolean deleteById(UUID id);
}