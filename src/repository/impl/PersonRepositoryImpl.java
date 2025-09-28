package repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import model.Person;
import repository.PersonRepository;

public class PersonRepositoryImpl implements PersonRepository {
    
    private List<Person> persons = new ArrayList<>();

    @Override
    public void save(Person person){
        persons.add(person);
    }

    @Override
    public  Optional<Person> findById(UUID id){
        return persons.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst();
    }

    @Override 
    public Optional<Person> findByEmail(String email){
        return persons.stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Person> findAll(){
        return new ArrayList<>(persons);
    }

    @Override
    public boolean deleteById(UUID id){
        return findById(id).map(p -> persons.remove(p)).orElse(false);
    }

}