package com.example.d3;

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

/**
 * TanFuncDeliverable3 - Tangent Calculator.
 *
 * @version 1.2.0
 */
public class TanFuncDeliverable3 extends Application {
  /**
   * INSETS.
   */
  private static final int INSETS = 20;
  /**
   * Vertical gap.
   */
  private static final int V_GAP = 10;
  /**
   * Horizontal gap.
   */
  private static final int H_GAP = 10;
  /**
   * Preffered Height.
   */
  private static final int PREF_HEIGHT = 40;
  /**
   * Minimum Width.
   */
  private static final int MIN_WIDTH = 100;
  /**
   * Column.
   */
  private static final int COL = 1;
  /**
   * Row.
   */
  private static final int ROW = 3;
  /**
   * Width of the Scene.
   */
  private static final int SCENE_WIDTH = 450;
  /**
   * Height of the Scene.
   */
  private static final int SCENE_HEIGHT = 250;
  /**
   * CSS text fill.
   */
  private static final String CSS_TEXT_FILL = "-fx-text-fill: #333333;";

  /**
   * Main method.
   *
   * @param args Arguments
   */
  public static void main(final String[] args) {
    launch(args);
  }

  /**
   * Starts the JavaFX application by setting up the UI components.
   *
   * @param primaryStage The primary stage for this application
   */
  @Override
  public void start(final Stage primaryStage) {
    primaryStage.setTitle("Tan(x) Calculator");

    GridPane grid = new GridPane();
    grid.setStyle("-fx-background-color: #f0f0f0;");
    grid.setPadding(new Insets(INSETS));
    grid.setVgap(V_GAP);
    grid.setHgap(H_GAP);

    // Title
    Label titleLabel = new Label("Tangent Calculator");
    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; "
        + CSS_TEXT_FILL);
    titleLabel.setAccessibleText("Title: Tangent Calculator");
    GridPane.setConstraints(titleLabel, 0, 0, 2, 1);
    titleLabel.setAlignment(Pos.CENTER);

    // Input
    Label inputLabel = new Label("Enter angle in radians:");
    inputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"
        + CSS_TEXT_FILL);
    inputLabel.setAccessibleText("Label: Enter angle in radians");
    GridPane.setConstraints(inputLabel, 0, 1);

    TextField inputField = new TextField();
    inputField.setPromptText("e.g., 0.0");
    inputField.setStyle("-fx-font-size: 14px;");
    inputField.setAccessibleText("Input field for angle in radians");
    GridPane.setConstraints(inputField, 1, 1);

    // Output
    Label outputLabel = new Label("Tangent:");
    outputLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"
        + CSS_TEXT_FILL);
    outputLabel.setAccessibleText("Label: Tangent result");
    GridPane.setConstraints(outputLabel, 0, 2);

    TextField outputField = new TextField();
    outputField.setEditable(false);
    outputField.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; "
        + "-fx-text-fill: #008CBA; -fx-background-color: #f0f0f0;");
    outputField.setPrefHeight(PREF_HEIGHT);
    outputField.setAccessibleText("Output field for tangent result");
    GridPane.setConstraints(outputField, 1, 2);


    // Calculate button
    Button calcButton = new Button("Calculate");
    calcButton.setMinWidth(MIN_WIDTH);
    calcButton.setStyle("-fx-font-size: 14px; "
        + "-fx-background-color: #4CAF50; -fx-text-fill: white;");
    calcButton.setAccessibleText("Calculate button");
    GridPane.setConstraints(calcButton, COL, ROW);

    calcButton.setOnAction(e -> {
      try {
        double x = Double.parseDouble(inputField.getText());
        double result = TanFunction.tan(x);
        outputField.setText(Double.toString(result));
      } catch (NumberFormatException ex) {
        showAlert(AlertType.ERROR, "Invalid Input",
            "Please enter a valid number.");
      } catch (IllegalArgumentException ex) {
        showAlert(AlertType.ERROR, "Undefined Value", ex.getMessage());
      }
    });

    // Clear button
    Button clearButton = new Button("Clear");
    clearButton.setMinWidth(MIN_WIDTH);
    clearButton.setStyle("-fx-font-size: 14px; -fx-background-color: #0000FF;"
        + "-fx-text-fill: white;");
    clearButton.setAccessibleText("Clear button");
    GridPane.setConstraints(clearButton, 0, ROW);

    clearButton.setOnAction(e -> {
      inputField.clear();
      outputField.clear();
    });

    // Exit button
    Button exitButton = new Button("Exit");
    exitButton.setMinWidth(MIN_WIDTH);
    exitButton.setStyle("-fx-font-size: 14px; -fx-background-color: #f44336;"
        + "-fx-text-fill: white;");
    exitButton.setAccessibleText("Exit button");
    GridPane.setConstraints(exitButton, 2, ROW);
    exitButton.setOnAction(e ->
        primaryStage.close()
    );

    // Adding components
    grid.getChildren().addAll(titleLabel, inputLabel, inputField,
        outputLabel, outputField, calcButton, clearButton, exitButton);

    Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Shows an alert dialog with the specified type, title, and message.
   *
   * @param alertType The type of the alert
   * @param title     The title of the alert dialog
   * @param message   The message to display in the alert dialog
   */
  private void showAlert(final AlertType alertType, final String title,
                         final String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
