package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #F8F8F8;");

        // Title
        Label title = new Label("iTrash Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.GREEN);

        // Username/Email field
        TextField usernameOrEmailField = new TextField();
        usernameOrEmailField.setPromptText("Username or Email");
        usernameOrEmailField.setMaxWidth(300);
        usernameOrEmailField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(200);
        loginButton.setStyle("-fx-background-color: #004d00; -fx-text-fill: white; -fx-background-radius: 10;");

        // Add event handling for login button
        loginButton.setOnAction(event -> {
            String usernameOrEmail = usernameOrEmailField.getText();
            String password = passwordField.getText();

            if (authenticateUser(usernameOrEmail, password)) {
                // Navigate to MonthFilterScreen
                MonthFilterScreen monthFilterScreen = new MonthFilterScreen();
                try {
                    monthFilterScreen.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Back to Sign Up hyperlink
        Hyperlink backToSignUp = new Hyperlink("Create a new account");
        backToSignUp.setTextFill(Color.GREEN);
        backToSignUp.setUnderline(true);

        // Add event handling to the backToSignUp hyperlink
        backToSignUp.setOnAction(event -> {
            NewUserScreen newUserScreen = new NewUserScreen();
            try {
                newUserScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add elements to the layout
        root.getChildren().addAll(
                title,
                usernameOrEmailField,
                passwordField,
                loginButton,
                backToSignUp
        );

        // Scene and Stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean authenticateUser(String usernameOrEmail, String password) {
        String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iTrash?useSSL=false", "root", "kenrick");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usernameOrEmail);
            preparedStatement.setString(2, usernameOrEmail);
            preparedStatement.setString(3, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // If a record exists, the credentials are valid
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
