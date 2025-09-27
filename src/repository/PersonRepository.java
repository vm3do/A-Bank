package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import model.Person;

public interface PersonRepository {

    Optional<Person> findById(UUID id);
    Optional<Person> findByEmail(String email);
    List <Person> findAll();
    void save(Person person);
    boolean deleteById(UUID id);

}