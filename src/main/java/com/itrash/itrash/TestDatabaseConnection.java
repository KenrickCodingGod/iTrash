package com.itrash.itrash;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/iTrash?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root"; // Replace with your MySQL username
        String password = "iTrashRoot"; // Replace with your MySQL password

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace for debugging
        }
    }
}
