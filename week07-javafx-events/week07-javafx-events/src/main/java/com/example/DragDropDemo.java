
package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragDropDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label dragLabel = new Label("Drag Me!");
        Label dropLabel = new Label("Drop Here");

        dragLabel.setStyle(
                "-fx-padding: 20; -fx-background-color: lightblue;"
        );

        dropLabel.setStyle(
                "-fx-padding: 30; -fx-background-color: lightgreen;"
        );

        dragLabel.setOnDragDetected(event -> {
            var dragboard = dragLabel.startDragAndDrop(
                    TransferMode.MOVE
            );

            ClipboardContent content = new ClipboardContent();
            content.putString(dragLabel.getText());
            dragboard.setContent(content);

            event.consume();
        });

        dropLabel.setOnDragOver(event -> {
            if (event.getGestureSource() != dropLabel
                    && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        dropLabel.setOnDragDropped(event -> {
            var dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                dropLabel.setText(dragboard.getString());
                dragLabel.setText("Moved!");
                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });

        VBox root = new VBox(30,
                new HBox(30, dragLabel, dropLabel)
        );

        root.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(root, 450, 250);

        primaryStage.setTitle("Drag and Drop Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
