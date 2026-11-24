package com.mycompany.programmaticdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LayoutDemo extends Application {

    @Override
    public void start(Stage stage) {

        // HBox: Arrange buttons horizontally
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(
                new Button("Home"),
                new Button("About"),
                new Button("Contact")
        );

        // VBox: Arrange components vertically
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("Welcome to JavaFX"),
                new Button("Click Me")
        );

        // GridPane: Arrange components in rows and columns
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(new Button("Enter Name"), 1, 0);
        grid.add(new Label("Course:"), 0, 1);
        grid.add(new Button("JavaFX"), 1, 1);

        // BorderPane: Divide the window into five regions
        BorderPane root = new BorderPane();

        root.setTop(hbox);
        root.setLeft(vbox);
        root.setCenter(grid);
        root.setBottom(new Label("JavaFX Layout Demo"));

        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("JavaFX Layout Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
