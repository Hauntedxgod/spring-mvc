package ru.maxima.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getLong("id"));
        person.setAge(rs.getInt("age"));
        person.setName(rs.getString("name"));
        person.setEmail(rs.getString("email"));


        return person;
    }
}
