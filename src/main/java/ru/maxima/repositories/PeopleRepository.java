package ru.maxima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.model.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends
        JpaRepository<Person , Long> {
    List<Person> findAllByNameLike(String like);


}
