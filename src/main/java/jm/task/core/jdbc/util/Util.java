package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/test_one",
                            "admin", "POI456ert");

            if (!conn.isClosed()) {
                System.out.println("соединение с БД установлено!");
            }


        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("с установкой соединения возникли проблемы");

        }
        return conn;
    }

    public Util()  {

    }


}
