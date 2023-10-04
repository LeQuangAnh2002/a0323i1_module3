package com.example.usermanagement.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public  static  Connection getConnection(){
            Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mySQL://127.0.0.1:3306/demo";
            String name = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url,name,password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
