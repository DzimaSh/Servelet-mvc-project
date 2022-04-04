package org.example.dao;

import org.example.exceptions.PersonNotFoundException;
import org.example.models.Person;
import org.example.service.DataBaseService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PersonDAO {

    private DataBaseService dataBaseService;
    private static PersonDAO instance = null;
    private Connection connection;

    public static PersonDAO getInstance() {
        if (instance == null)
            instance = new PersonDAO();
        return instance;
    }
    private PersonDAO() {
        dataBaseService = new DataBaseService();
        connection = dataBaseService.openDataBase();
    }

    public List<Person> getAllPeople() {
        List<Person> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.
                    prepareStatement("SELECT * FROM person").
                    executeQuery();
            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Person getPersonById(Integer id) throws PersonNotFoundException{
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                throw new PersonNotFoundException("Person with id: " + id + " does not exists");
            person = new Person(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void createPerson(Person person) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO person (name, age, email) VALUES (?, ?, ?) ");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePerson(Integer id, Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setInt(4, id);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<ConstraintViolation<Person>> validatePerson(Person person){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(person);
    }
}
