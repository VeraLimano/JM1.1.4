package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Util util = new Util();
    Session session;
    Transaction transaction;

    {
        try {
            session = util.getSessionFactory().openSession();
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user1 (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        session.createSQLQuery(sql).executeUpdate();
        System.out.println("Создание таблицы");
    }

    @Override
    public void dropUsersTable() {
        session.createSQLQuery("DROP TABLE IF EXISTS user1").executeUpdate();
        System.out.println("Удаление таблицы");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session.save(user);
        transaction = session.beginTransaction();
        transaction.commit();
        System.out.println("Добавление User(ов) в таблицу");
    }

    @Override
    public void removeUserById(long id) {
        User user = (User) session.get(User.class, id);
        transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        System.out.println("Удаление User из таблицы");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = session.createCriteria(User.class).list();
        System.out.println(list);
        System.out.println("Получение всех User(ов) из таблицы");
        return list;
    }

    @Override
    public void cleanUsersTable() {
        transaction = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        for (int i = 0; i < list.size(); i ++) {
            session.delete(list.get(i));
        }
        transaction.commit();
        System.out.println("Очистка содержания таблицы");
    }
}
