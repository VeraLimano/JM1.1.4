package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();

        dao.createUsersTable();

        dao.saveUser("Jim", "Kerry", (byte) 45);
        dao.saveUser("Margot", "Robbie", (byte) 30);
        dao.saveUser("John", "Depp", (byte) 57);
        dao.saveUser("Tom", "Cruise", (byte) 58);

        dao.getAllUsers();

        dao.cleanUsersTable();
        dao.dropUsersTable();
    }
}
