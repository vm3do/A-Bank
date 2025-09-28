package service;

import repository.PersonRepository;

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

        Optional<Person> existing = personRepo.findByEmail(client.getEmail());
        if(existing.isPresent()){
            throw new IllegalArgumentException("Email already exists !");
        }
        personRepo.save(client);

    }

    public void registerManager(Manager manager) {
        Optional<Person> existing = personRepo.findByEmail(manager.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + manager.getEmail());
        }
        personRepo.save(manager);
    }

    public Optional<Person> login(String email, String password){
        return personRepo.findByEmail(email)
                            .filter(p -> p.getPassword().equals(password));
    }
    
}