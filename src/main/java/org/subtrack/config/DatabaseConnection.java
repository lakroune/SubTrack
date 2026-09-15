package org.subtrack.config;

import java.sql.Connection;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/subtrack_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
