package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends Application {

    // Mock stored credentials
    private final Map<String, String> storedCredentials = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        // Initialize stored credentials
        storedCredentials.put("user@example.com", "password123");
        storedCredentials.put("admin@example.com", "adminpassword");

        // Root layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #F8F8F8;");

        // Title
        Label title = new Label("iTrash");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.GREEN);

        // Subtitle
        Label subtitle = new Label("Keeping Our Parks and Cities Green and Clean");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.BLACK);

        // Email field
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Login button
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(200);
        loginButton.setStyle("-fx-background-color: #004d00; -fx-text-fill: white; -fx-background-radius: 10;"); // Dark green background

        // Forgot Password link
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.GREEN);
        forgotPassword.setUnderline(true);

        // New User hyperlink
        Hyperlink newUserLink = new Hyperlink("New User?");
        newUserLink.setTextFill(Color.GREEN);
        newUserLink.setUnderline(true);

        // Add event handling to the login button
        loginButton.setOnAction(event -> {
            String enteredEmail = emailField.getText();
            String enteredPassword = passwordField.getText();

            // Check if the entered email and password match the stored credentials
            if (storedCredentials.containsKey(enteredEmail) &&
                    storedCredentials.get(enteredEmail).equals(enteredPassword)) {
                System.out.println("Login successful for " + enteredEmail);
            } else {
                // Show login failed message
                System.out.println("Login failed. Invalid credentials.");
            }
        });

        // Forgot Password hyperlink event handling
        forgotPassword.setOnAction(event -> {
            SendLinkScreen sendLinkScreen = new SendLinkScreen(); // Correct class name
            try {
                sendLinkScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // New User hyperlink event handling
        newUserLink.setOnAction(event -> {
            NewUserScreen newUserScreen = new NewUserScreen();
            try {
                newUserScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add elements to the layout
        root.getChildren().addAll(title, subtitle, emailField, passwordField, loginButton, forgotPassword, newUserLink);

        // Scene and Stage
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}//hello
