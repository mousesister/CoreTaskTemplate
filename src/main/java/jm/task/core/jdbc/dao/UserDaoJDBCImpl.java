package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection connection = util.getConnection();
    Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS MyUsers (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(50), " +
                "lastName VARCHAR(50), age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate(createTable);
            //connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP TABLE IF EXISTS MyUsers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO MyUsers (name, lastName, age) VALUES (?, ?, ?)");

            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            statement.execute("DELETE FROM MyUsers WHERE id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MyUsers");
             ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM MyUsers")) {
             while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try  {
            statement.executeUpdate("DELETE FROM MyUsers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}