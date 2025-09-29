package service;

import repository.PersonRepository;
import utils.Validation;

import java.util.Optional;

import model.Person;
import model.Client;
import model.Manager;

public class AuthService {

    private final PersonRepository personRepo;

    public AuthService(PersonRepository personRepo){
        this.personRepo = personRepo;
    }

    public void registerClient(Client client) {
        validatePerson(client);
        checkEmailExists(client.getEmail());
        personRepo.save(client);
    }

    public void registerManager(Manager manager) {
        validatePerson(manager);
        checkEmailExists(manager.getEmail());
        personRepo.save(manager);
    }
    
    private void validatePerson(Person person) {
        if (!Validation.isValidName(person.getName())) {
            throw new IllegalArgumentException("Invalid name. Name must be at least 2 characters long.");
        }
        if (!Validation.isValidEmail(person.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!Validation.isValidPassword(person.getPassword())) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }
    }
    
    private void checkEmailExists(String email) {
        Optional<Person> existing = personRepo.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
    }

    public Optional<Person> login(String email, String password){

        if (!Validation.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!Validation.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }
        
        return personRepo.findByEmail(email)
                            .filter(p -> p.getPassword().equals(password));
    }
    
}