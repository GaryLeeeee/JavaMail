package com.garylee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//加载jdbc驱动和连接数据库
public class Jdbc {
    public Jdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail?characterEncoding=UTF-8", "root",
                "admin");
    }
}
