package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mybdtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    Connection connection;


    public Util() {
        try {
//            Driver driver = new com.mysql.jdbc.Driver();
//            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            System.out.println("no conect");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
