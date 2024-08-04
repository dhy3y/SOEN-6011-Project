package com.example.d2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TanFuncDeliverable2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application by setting up the UI components.
     *
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tan(x) Calculator");

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #f0f0f0;");
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Title
        Label titleLabel = new Label("Tangent Calculator");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);
        titleLabel.setAlignment(Pos.CENTER);

        // Input
        Label inputLabel = new Label("Enter angle in radians:");
        inputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        GridPane.setConstraints(inputLabel, 0, 1);

        TextField inputField = new TextField();
        inputField.setPromptText("e.g., 0.0");
        inputField.setStyle("-fx-font-size: 14px;");
        GridPane.setConstraints(inputField, 1, 1);

        // Output
        Label outputLabel = new Label("Tangent:");
        outputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        GridPane.setConstraints(outputLabel, 0, 2);

        TextField outputField = new TextField();
        outputField.setEditable(false);
        outputField.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #008CBA; -fx-background-color: #f0f0f0;");
        outputField.setPrefHeight(40);
        GridPane.setConstraints(outputField, 1, 2);


        // Calculate button
        Button calcButton = new Button("Calculate");
        calcButton.setMinWidth(100);
        calcButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        GridPane.setConstraints(calcButton, 1, 3);

        calcButton.setOnAction(e -> {
            try {
                double x = Double.parseDouble(inputField.getText());
                double result = tan(x);
                outputField.setText(Double.toString(result));
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Invalid Input", "Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                showAlert(AlertType.ERROR, "Undefined Value", ex.getMessage());
            }
        });

        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setMinWidth(100);
        clearButton.setStyle("-fx-font-size: 14px; -fx-background-color: #f44336; -fx-text-fill: white;");
        GridPane.setConstraints(clearButton, 0, 3);

        clearButton.setOnAction(e -> {
            inputField.clear();
            outputField.clear();
        });

        // Adding components
        grid.getChildren().addAll(titleLabel, inputLabel, inputField, outputLabel, outputField, calcButton, clearButton);

        Scene scene = new Scene(grid, 450, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Calculates the tangent of the given angle in radians.
     *
     * @param x The angle in radians
     * @return The tangent of the angle x
     */
    private double tan(double x) {
        x = normalize(x);

        if (x == 0) {
            return 0;
        }

        double sinX = sin(x);
        double cosX = cos(x);

        if (!isGreaterThan15Dig(cosX)) {
            throw new IllegalArgumentException("Tangent(x) is undefined for this input (too close to π/2 or its odd multiples).");
        }

        return sinX / cosX;
    }

    /**
     * Computes the sine of the given angle in radians using a Maclaurin series expansion.
     *
     * @param x The angle in radians
     * @return The sine of the angle x
     */
    private double sin(double x) {
        double term = x;
        double sum = x;
        for (int i = 3; isGreaterThan15Dig(term); i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    /**
     * Computes the cosine of the given angle in radians using a Maclaurin series expansion.
     *
     * @param x The angle in radians
     * @return The cosine of the angle x
     */
    private double cos(double x) {
        double term = 1;
        double sum = 1;
        for (int i = 2; isGreaterThan15Dig(term); i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    /**
     * Normalizes the angle x to be within the range of -π to +π radians.
     *
     */
    private static double normalize(double x) {
        double PI = 3.14159265358979323846;
        int integerPart = (int) ((x + PI) / (2 * PI));
        double val;

        if (x == integerPart) {
            val = x;
        }
        if (x < 0) {
            val = integerPart - 1;
        }
        else {
            val = integerPart;
        }

        return x - 2 * PI * val;
    }

    /**
     * Checks if the given value is greater than 1e-15 in magnitude.
     */
    private static boolean isGreaterThan15Dig(double value) {
        return value >= 0 ? value > 1e-15 : -value > 1e-15;
    }

    /**
     * Shows an alert dialog with the specified type, title, and message.
     *
     * @param alertType The type of the alert
     * @param title     The title of the alert dialog
     * @param message   The message to display in the alert dialog
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
