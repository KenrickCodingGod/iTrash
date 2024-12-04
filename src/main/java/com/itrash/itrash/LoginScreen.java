package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #4A90E2, #8ED1FC);");

        // Title
        Label title = new Label("iTrash");
        title.setFont(new Font("Arial", 30));
        title.setTextFill(Color.WHITE);

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
        loginButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-background-radius: 10;");

        // Forgot Password link
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.WHITE);
        forgotPassword.setUnderline(true);

        // New User hyperlink
        Hyperlink newUserLink = new Hyperlink("New User?");
        newUserLink.setTextFill(Color.WHITE);
        newUserLink.setUnderline(true);

        // Add elements to the layout
        root.getChildren().addAll(title, emailField, passwordField, loginButton, forgotPassword, newUserLink);

        // Scene and Stage
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
