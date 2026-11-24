
package com.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label statusLabel = new Label("Ready");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        Button startButton = new Button("Download File");

        startButton.setOnAction(e -> {

            startButton.setDisable(true);

            Task<Void> downloadTask = new Task<>() {

                @Override
                protected Void call() throws Exception {

                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        updateProgress(i, 100);
                        updateMessage("Downloading... " + i + "%");
                    }

                    return null;
                }
            };

            progressBar.progressProperty().bind(
                downloadTask.progressProperty()
            );

            statusLabel.textProperty().bind(
                downloadTask.messageProperty()
            );

            downloadTask.setOnSucceeded(event -> {
                startButton.setDisable(false);
                statusLabel.textProperty().unbind();
                statusLabel.setText("Download Complete!");
            });

            downloadTask.setOnFailed(event -> {
                startButton.setDisable(false);
                statusLabel.textProperty().unbind();
                statusLabel.setText("Download failed.");
            });

            Thread thread = new Thread(downloadTask);
            thread.setDaemon(true);
            thread.start();
        });

        VBox root = new VBox(20, startButton, progressBar, statusLabel);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Progress Bar Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
