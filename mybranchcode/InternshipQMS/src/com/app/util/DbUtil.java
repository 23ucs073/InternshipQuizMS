package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/quiz_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "manager";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
