package ru.maxima.dao;

import org.postgresql.Driver;
import org.springframework.cglib.transform.AbstractClassTransformer;
import org.springframework.stereotype.Component;
import ru.maxima.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Component
public class PersonDAO {
 // DAO = data access object - доступ к данным обьектам

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";


    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL , USERNAME , PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                allPeople.add(person);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return allPeople;
    }

    public Person findById(Long id) {
        Person person = null;
        try {

            PreparedStatement prepareStatement = connection.prepareStatement("select * from person where id = ? ");
            prepareStatement.setLong(1 , id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while(resultSet.next()){
                new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {
//        Person personWithMaxId = getAllPeople().stream().max(Comparator.comparing(Person::getId))
//                .orElseThrow(NoSuchFieldError::new);
//        Long nextId = (long) (personWithMaxId.getId() + 1);
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("insert into person( name , age, email) values (? , ?, ?)");
            prepareStatement.setString(1 , person.getName());
            prepareStatement.setInt(2 , person.getAge());
            prepareStatement.setString(3 , person.getEmail());
            prepareStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Long id, Person editedPerson) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update person " + " set name= ? , set age = ? , set email = ? "  + "Where id = ?");
            prepareStatement.setString(1 , editedPerson.getName());
            prepareStatement.setInt(2 , editedPerson.getAge());
            prepareStatement.setString(3 , editedPerson.getEmail());
            prepareStatement.setLong(4 , id);
            prepareStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void deleteById(Long id) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("delete from person  where id = ?");
            prepareStatement.setLong(1 , id );
            prepareStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
