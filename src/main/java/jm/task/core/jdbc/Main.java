package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl user = new UserDaoHibernateImpl();
        user.createUsersTable();

        user.saveUser("John", "Doe", (byte) 30);
        user.saveUser("Jane", "Smith", (byte) 35);
        user.saveUser("Michael", "Johnson", (byte) 45);
        user.saveUser("Emily", "Brown", (byte) 26);

        user.getAllUsers();
        System.out.println(user.getAllUsers().toString());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
