package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SendLinkScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #FFFFFF;"); // White background

        // Title
        Label title = new Label("iTrash");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.GREEN);

        // Subtitle
        Label subtitle = new Label("Send Password Reset Link");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.BLACK);

        // Email field
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;");

        // Send Link button
        Button sendLinkButton = new Button("Send Link");
        sendLinkButton.setMaxWidth(200);
        sendLinkButton.setStyle("-fx-background-color: #004d00; -fx-text-fill: white; -fx-background-radius: 10;");

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
        root.getChildren().addAll(title, subtitle, emailField, sendLinkButton, backToLogin);

        // Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Send Link");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
