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

    public void registerClient(Client client){

        if (!Validation.isValidName(client.getName())) {
            throw new IllegalArgumentException("Invalid name. Name must be at least 2 characters long.");
        }
        if (!Validation.isValidEmail(client.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!Validation.isValidPassword(client.getPassword())) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }

        Optional<Person> existing = personRepo.findByEmail(client.getEmail());
        if(existing.isPresent()){
            throw new IllegalArgumentException("Email already exists !");
        }
        personRepo.save(client);
    }

    public void registerManager(Manager manager) {

        if (!Validation.isValidName(manager.getName())) {
            throw new IllegalArgumentException("Invalid name. Name must be at least 2 characters long.");
        }
        if (!Validation.isValidEmail(manager.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!Validation.isValidPassword(manager.getPassword())) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }

        Optional<Person> existing = personRepo.findByEmail(manager.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + manager.getEmail());
        }
        personRepo.save(manager);
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