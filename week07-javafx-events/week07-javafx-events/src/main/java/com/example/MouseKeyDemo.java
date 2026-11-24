
package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MouseKeyDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label infoLabel = new Label("Interact with the mouse and keyboard!");

        VBox root = new VBox(20, infoLabel);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        // Mouse Events
        root.setOnMouseClicked(e -> {
            infoLabel.setText("Mouse Clicked!");
            System.out.println("Mouse Clicked at: "
                    + e.getX() + ", " + e.getY());
        });

        root.setOnMouseEntered(e -> {
            infoLabel.setText("Mouse Entered!");
        });

        root.setOnMouseExited(e -> {
            infoLabel.setText("Mouse Exited!");
        });

        // Key Events
        root.setOnKeyPressed(e -> {
            infoLabel.setText("Key Pressed: " + e.getCode());
            System.out.println("Key Pressed: " + e.getCode());
        });

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Mouse and Key Events");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
