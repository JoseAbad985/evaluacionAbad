package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivityTest {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Jjwm20020";

    public static void main(String[] args) {
        // Try to establish a connection to the database
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for more detailed error information
        }
    }
}
