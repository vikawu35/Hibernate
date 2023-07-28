package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getConnection();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql =
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(255) NOT NULL," +
                            "lastName VARCHAR(255) NOT NULL," +
                            "age TINYINT NOT NULL" +
                            ")";
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("An exception occurred while testing to create a user table\n");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("An exception occurred while testing drop table\n");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User with name - " + name + "  added to the database.");
        } catch (Exception ex) {
            System.out.println("User was incorrectly added to the database");
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            session.delete(user);
        } catch(Exception ex) {
            System.out.println("An exception occurred while testing deleting a user by id\n");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> user = session.createQuery("FROM User")
                    .getResultList();
            return user;
        }
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("An exception occurred while testing clearing the users table\n");
        }
    }
}
