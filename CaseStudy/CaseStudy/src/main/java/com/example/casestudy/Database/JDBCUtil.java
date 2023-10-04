package com.example.casestudy.Database;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public  static Connection getConnection(){
        Connection connection = null;
        try {
            DriverManager.registerDriver(new Driver());
            String url = "jdbc:mySQL://127.0.0.1:3306/furama_resort";
            String username= "root";
            String password = "123456";
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
