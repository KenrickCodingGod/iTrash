package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FileDetailScreen extends Application {

    private String selectedMonth = "Oktober"; // Dynamically set based on previous screen
    private String selectedFileDate = "1"; // Dynamically set based on clicked file

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #F8F8F8;");

        // Header Section
        HBox headerBox = new HBox(10);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        // Back Button
        Button backButton = new Button();
        ImageView backArrowIcon = new ImageView("file:src/main/resources/back_arrow.png");
        backArrowIcon.setFitWidth(20);
        backArrowIcon.setFitHeight(20);
        backButton.setGraphic(backArrowIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        backButton.setOnAction(event -> {
            MonthFilterScreen monthFilterScreen = new MonthFilterScreen();
            try {
                monthFilterScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Label title = new Label("iTrash");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.GREEN);

        headerBox.getChildren().addAll(backButton, title);
        headerBox.setSpacing(10);

        // Date and Month Label
        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);

        Label dateLabel = new Label(selectedFileDate + " " + selectedMonth); // Correctly formatted
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        dateLabel.setTextFill(Color.BLACK);

        dateBox.getChildren().add(dateLabel);

        // Video Scroll Box
        GridPane videoGrid = new GridPane();
        videoGrid.setHgap(10);
        videoGrid.setVgap(10);
        videoGrid.setAlignment(Pos.CENTER);

        populateVideoGrid(videoGrid);

        ScrollPane scrollPane = new ScrollPane(videoGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400); // Adjusted scroll box height
        scrollPane.setStyle("-fx-background: #FFFFFF; -fx-border-radius: 10; -fx-padding: 5;");

        // Add Components to Root Layout
        root.getChildren().addAll(headerBox, dateBox, scrollPane);

        // Set the Scene and Stage
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("File Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void populateVideoGrid(GridPane videoGrid) {
        // Mock video file data with timestamps
        List<String> videoTimestamps = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            videoTimestamps.add(String.format("%02d:00", i)); // e.g., "01:00", "02:00", etc.
        }

        int col = 0, row = 0;

        for (String timestamp : videoTimestamps) {
            VBox videoBox = new VBox(5);
            videoBox.setAlignment(Pos.CENTER);

            // Video Icon
            ImageView videoIcon = new ImageView("file:src/main/resources/video_icon.png");
            videoIcon.setFitWidth(50);
            videoIcon.setFitHeight(50);

            // Timestamp Label
            Label timestampLabel = new Label(timestamp);
            timestampLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            timestampLabel.setTextFill(Color.BLACK);

            videoBox.getChildren().addAll(videoIcon, timestampLabel);
            videoGrid.add(videoBox, col, row);

            col++;
            if (col > 2) { // Max 3 columns per row
                col = 0;
                row++;
            }
        }
    }

    public void setSelectedMonth(String month) {
        this.selectedMonth = month;
    }

    public void setSelectedFileDate(String fileDate) {
        this.selectedFileDate = fileDate;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
