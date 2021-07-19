package jm.task.core.jdbc;


import jm.task.core.jdbc.util.Util;
import java.sql.*;


public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        Statement statement;
        try {
            statement = util.getConnection().createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS User(id int not null primary key AUTO_INCREMENT, " +
                    "name varchar(50) NOT NULL default 'Jone', lastName varchar(40) NOT NULL default 'Dow', " +
                    "age tinyint NOT NULL default 25)";
            statement.execute(createTable);
            statement.executeUpdate("INSERT INTO User(name, lastName, age) VALUES ('Jone', 'Dow', 42), " +
                                                "('Jane', 'Dow', 51), ('Thomas', 'Hanks', 65), ('Rita', 'Hayworth', 103) ");

            ResultSet rs = statement.executeQuery("SELECT * FROM User");
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println("User with name: " + name + " has been added to the database");
            }
            statement.executeUpdate("TRUNCATE TABLE User");
            statement.executeUpdate("DROP TABLE IF EXISTS User");
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("Соединение с базой закрыто");
            }


        } catch(SQLException se){
                System.out.println("что-то пошло не так");
                se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
