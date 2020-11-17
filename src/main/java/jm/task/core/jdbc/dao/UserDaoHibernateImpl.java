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

    @Override
    public void createUsersTable() {
        try {
            session = util.getSessionFactory().openSession();
            String sql = "CREATE TABLE IF NOT EXISTS user1 (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
            session.createSQLQuery(sql).executeUpdate();
            System.out.println("Создание таблицы");
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = util.getSessionFactory().openSession();
            session.createSQLQuery("DROP TABLE IF EXISTS user1").executeUpdate();
            System.out.println("Удаление таблицы");
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = util.getSessionFactory().openSession();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction = session.beginTransaction();
            transaction.commit();
            System.out.println("Добавление User(ов) в таблицу");
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = util.getSessionFactory().openSession();
            User user = (User) session.get(User.class, id);
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            System.out.println("Удаление User из таблицы");
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            session = util.getSessionFactory().openSession();
        } catch (Exception throwables) {
            System.out.println(throwables);
        }

        List<User> list = session.createCriteria(User.class).list();
        System.out.println(list);
        System.out.println("Получение всех User(ов) из таблицы");
        return list;
    }

    @Override
    public void cleanUsersTable() {

        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<User> list = session.createCriteria(User.class).list();
            for (int i = 0; i < list.size(); i ++) {
                session.delete(list.get(i));
            }
            transaction.commit();
            System.out.println("Очистка содержания таблицы");
        } catch (Exception throwables) {
            System.out.println(throwables);
        }
    }
}
