package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import javax.lang.model.type.ArrayType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            stmt = conn.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS User(id int not null primary key AUTO_INCREMENT, " +
                    "name varchar(50) NOT NULL default 'Jone', lastName varchar(40) NOT NULL default 'Dow', " +
                    "age tinyint NOT NULL default 25)";
            stmt.executeQuery(createTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.rollback();
            throw ex;
        } finally {
            stmt.close();
            conn.close();
        }


    }

    @Override
    public void dropUsersTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            stmt = conn.createStatement();
            stmt.executeQuery("DROP TABLE User");
        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.rollback();
            throw ex;
        } finally {
            stmt.close();
            conn.close();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT * FROM User");
            List<User> result = new ArrayList<>();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String lastName = res.getString("lastName");
                byte age = res.getByte("age");
                User user = new User(id);
                user.setName(name);
                user.getLastName(lastName);
                user.getAge(age);
                result.add(user);
            }
            conn.commit();
            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.rollback();
            throw ex;
        } finally {
            res.close();
            stmt.close();
            conn.close();
        }

    }

    @Override
    public void cleanUsersTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            stmt = conn.createStatement();
            stmt.executeQuery("TRUNCATE TABLE User");
        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.rollback();
            throw ex;
        } finally {
            stmt.close();
            conn.close();
        }

    }
}
