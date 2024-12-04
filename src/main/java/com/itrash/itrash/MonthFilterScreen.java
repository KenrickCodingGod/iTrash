package com.itrash.itrash;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MonthFilterScreen extends Application {

    private VBox fileContainer; // Holds the scrollable file grid
    private Label selectedMonthLabel; // Displays the selected month
    private Label placeholder; // Placeholder text for empty state

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #F8F8F8;");

        // Header section
        VBox header = new VBox(5);
        header.setAlignment(Pos.CENTER);

        Label title = new Label("iTrash");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.GREEN);

        Label subtitle = new Label("Keeping Our Parks and Cities Green and Clean");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.GREEN);

        header.getChildren().addAll(title, subtitle);

        // Month filter section
        HBox filterBox = new HBox(10);
        filterBox.setAlignment(Pos.CENTER);

        ComboBox<String> monthFilter = new ComboBox<>();
        monthFilter.setPromptText("Select a Month");
        monthFilter.setStyle("-fx-font-size: 14px;");
        for (Month month : Month.values()) {
            monthFilter.getItems().add(month.name());
        }

        selectedMonthLabel = new Label();
        selectedMonthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        selectedMonthLabel.setTextFill(Color.BLACK);

        monthFilter.setOnAction(event -> {
            String selectedMonth = monthFilter.getValue();
            if (selectedMonth != null) {
                selectedMonthLabel.setText(selectedMonth);
                populateFilesForMonth(selectedMonth, primaryStage);
            }
        });

        filterBox.getChildren().addAll(monthFilter);

        // Placeholder for file grid
        fileContainer = new VBox(15);
        fileContainer.setAlignment(Pos.CENTER);

        placeholder = new Label("Video folders will be displayed here once a month is chosen.");
        placeholder.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        placeholder.setTextFill(Color.GRAY);
        placeholder.setWrapText(true);
        placeholder.setMaxWidth(300);
        placeholder.setAlignment(Pos.CENTER);

        fileContainer.getChildren().add(placeholder);

        // ScrollPane for fileContainer
        ScrollPane scrollPane = new ScrollPane(fileContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #F8F8F8; -fx-border-radius: 10;");
        scrollPane.setPrefHeight(400);

        // Pagination controls
        HBox pagination = new HBox();
        pagination.setAlignment(Pos.BOTTOM_RIGHT);
        pagination.setPadding(new Insets(10));

        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        nextButton.setTextFill(Color.GREEN); // Set text color to green
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        pagination.getChildren().add(nextButton);

        nextButton.setOnAction(event -> {
            // Logic for Next button (can be extended based on pagination functionality)
            System.out.println("Next button clicked");
        });

        // Add components to root layout
        root.getChildren().addAll(header, filterBox, selectedMonthLabel, scrollPane, pagination);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Month Filter Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void populateFilesForMonth(String month, Stage primaryStage) {
        fileContainer.getChildren().clear(); // Clear previous files
        GridPane fileGrid = new GridPane();
        fileGrid.setHgap(10);
        fileGrid.setVgap(10);
        fileGrid.setPadding(new Insets(10));
        fileGrid.setAlignment(Pos.CENTER);

        // Get the maximum number of days in the selected month (non-leap year for simplicity)
        YearMonth yearMonth = YearMonth.of(2024, Month.valueOf(month.toUpperCase()));
        int maxDays = yearMonth.lengthOfMonth();

        List<String> fileDates = new ArrayList<>();
        for (int i = 1; i <= maxDays; i++) {
            fileDates.add(i + " " + month);
        }

        int col = 0;
        int row = 0;

        for (String date : fileDates) {
            VBox fileBox = new VBox(5);
            fileBox.setAlignment(Pos.CENTER);

            ImageView folderIcon = new ImageView("file:src/main/resources/file_icon.png");
            folderIcon.setFitWidth(50);
            folderIcon.setFitHeight(50);

            Label folderLabel = new Label("Folder");
            folderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            folderLabel.setTextFill(Color.BLACK);

            Label dateLabel = new Label(date);
            dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            dateLabel.setTextFill(Color.BLACK);

            fileBox.getChildren().addAll(folderIcon, folderLabel, dateLabel);

            // Add event to navigate to FileDetailScreen
            String selectedDate = date; // Make the date effectively final
            folderIcon.setOnMouseClicked(event -> {
                FileDetailScreen fileDetailScreen = new FileDetailScreen();
                fileDetailScreen.setSelectedMonth(month);
                fileDetailScreen.setSelectedFileDate(selectedDate);
                try {
                    fileDetailScreen.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            fileGrid.add(fileBox, col, row);

            col++;
            if (col > 2) { // 3 columns per row
                col = 0;
                row++;
            }
        }

        fileContainer.getChildren().add(fileGrid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
