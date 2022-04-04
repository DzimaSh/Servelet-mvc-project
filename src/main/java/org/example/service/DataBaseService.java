package org.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DataBaseService {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD ;
    private static Connection connection = null;

    public DataBaseService() {
        try (InputStream input = DataBaseService.class.getClassLoader().getResourceAsStream("dbProperties.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            URL = prop.getProperty("dbProperties.URL");
            USERNAME = prop.getProperty("dbProperties.USERNAME");
            PASSWORD = prop.getProperty("dbProperties.PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Connection openDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT EXISTS(SELECT from pg_tables WHERE schemaname = 'public' AND tablename = 'person');"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean("exists")) {
                connection.prepareStatement(
                                "CREATE TABLE person(id SERIAL PRIMARY KEY, name VARCHAR, age INT, email VARCHAR)").
                        executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

