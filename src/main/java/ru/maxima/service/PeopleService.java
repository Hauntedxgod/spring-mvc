package ru.maxima.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.controllers.AdminController;
import ru.maxima.model.Person;
import ru.maxima.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository repository;

    @Autowired
    public PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }


    public List<Person> getAllPeople() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Transactional
    public void save(Person person) {
        repository.save(person);
    }

    @Transactional
    public void update(Long id, Person editedPerson) {
        editedPerson.setId(id);
        repository.save(editedPerson);
    }


    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void upgradeToAdmin(Person person) {
        person.setAdmin(true);
        repository.save(person);
    }
}
