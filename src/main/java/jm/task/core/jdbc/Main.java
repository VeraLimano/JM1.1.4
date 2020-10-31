package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        UserServiceImpl dao = new UserServiceImpl();

        dao.createUsersTable();

        dao.saveUser("Jim", "Kerry", (byte) 45);
        dao.saveUser("Margot", "Robbie", (byte) 30);
        dao.saveUser("John", "Depp", (byte) 57);
        dao.saveUser("Tom", "Cruise", (byte) 58);

        dao.getAllUsers();

        dao.cleanUsersTable();
        dao.dropUsersTable();

        util.getSessionFactory().close();
        System.out.println(util.getSessionFactory().isClosed());
    }
}
