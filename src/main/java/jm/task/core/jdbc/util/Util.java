package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    Connection conn;

    public Connection getConnection() {
        return conn;
    }

    public Util()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/test_one",
                            "admin", "POI456ert");
            //conn.setAutoCommit(false);
            if (!conn.isClosed()) {
                System.out.println("соединение с БД установлено!");
            }


        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");

        }
    }


}
