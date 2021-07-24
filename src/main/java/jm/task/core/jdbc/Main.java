package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NullPointerException, SQLException {
        final User USER1 = new User("John", "Dow", (byte) 42);
        final User USER2 = new User("Jane", "Dow", (byte) 51);
        final User USER3 = new User("Thomas", "Hanks", (byte) 65);
        final User USER4 = new User("Rita", "Hayworth", (byte) 103);

        final UserDao worker = new UserServiceImpl();

       worker.createUsersTable();

        worker.saveUser(USER1.getName(), USER1.getLastName(), USER1.getAge());
        System.out.println("User с именем: " + USER1.getName() + " добавлен в базу данных");
        worker.saveUser(USER2.getName(), USER2.getLastName(), USER2.getAge());
        System.out.println("User с именем: " + USER2.getName() + " добавлен в базу данных");
        worker.saveUser(USER3.getName(), USER3.getLastName(), USER3.getAge());
        System.out.println("User с именем: " + USER3.getName() + " добавлен в базу данных");
        worker.saveUser(USER4.getName(), USER4.getLastName(), USER4.getAge());
        System.out.println("User с именем: " + USER4.getName() + " добавлен в базу данных");

        List<User> users = worker.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        worker.cleanUsersTable();
        worker.dropUsersTable();

    }
}
