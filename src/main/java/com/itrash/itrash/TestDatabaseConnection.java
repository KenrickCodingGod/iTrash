package com.itrash.itrash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        // Define the database connection details
        String url = "jdbc:mysql://localhost:3306/iTrash?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root"; // Replace with your MySQL username
        String password = "kenrick"; // Replace with your MySQL password

        // Attempt to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace(); // Print the full stack trace for debugging
        }
    }
}
