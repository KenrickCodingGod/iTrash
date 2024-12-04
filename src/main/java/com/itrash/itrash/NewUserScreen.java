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
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class NewUserScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
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

        // Container for fields and their labels
        VBox fieldContainer = new VBox(10);
        fieldContainer.setAlignment(Pos.CENTER_LEFT);

        // First Name field
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setMaxWidth(300);
        firstNameField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Last Name field
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setMaxWidth(300);
        lastNameField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);
        usernameField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Email field
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Phone number field
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setMaxWidth(300);
        phoneField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Confirm password field
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setMaxWidth(300);
        confirmPasswordField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Add labeled fields with "Required*" text
        fieldContainer.getChildren().addAll(
                createLabeledField("First Name", firstNameField),
                createLabeledField("Last Name", lastNameField),
                createLabeledField("Username", usernameField),
                createLabeledField("Email", emailField),
                createLabeledField("Phone Number", phoneField),
                createLabeledField("Password", passwordField),
                createLabeledField("Confirm Password", confirmPasswordField)
        );

        // Create Account button
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setMaxWidth(200);
        createAccountButton.setStyle("-fx-background-color: #004d00; -fx-text-fill: white; -fx-background-radius: 10;");

        // Back to Login hyperlink
        Hyperlink backToLogin = new Hyperlink("Back to Login");
        backToLogin.setTextFill(Color.GREEN);
        backToLogin.setUnderline(true);

        // Add event handling to the backToLogin hyperlink
        backToLogin.setOnAction(event -> {
            LoginScreen loginScreen = new LoginScreen();
            try {
                loginScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add elements to the layout
        root.getChildren().addAll(
                title,
                subtitle,
                fieldContainer,
                createAccountButton,
                backToLogin
        );

        // Scene and Stage
        Scene scene = new Scene(root, 400, 700);
        primaryStage.setTitle("New User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void insertUser(String username, String firstName, String lastName, String email, String phoneNumber, String password) {
        String query = "INSERT INTO users (username, first_name, last_name, email, phone_number, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iTrash?useSSL=false", "root", "iTrashRoot");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, password); // Preferably hashed

            preparedStatement.executeUpdate();
            System.out.println("User inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private VBox createLabeledField(String fieldName, TextField field) {
        VBox labeledField = new VBox(5);
        labeledField.setAlignment(Pos.CENTER_LEFT);

        HBox requiredText = new HBox();
        requiredText.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(fieldName + " ");
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        label.setTextFill(Color.BLACK);

        Label asterisk = new Label("*");
        asterisk.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        asterisk.setTextFill(Color.RED);

        requiredText.getChildren().addAll(label, asterisk);
        labeledField.getChildren().addAll(requiredText, field);

        return labeledField;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
