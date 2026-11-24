
package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomEventDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label messageLabel = new Label("Click login to continue.");
        Button loginButton = new Button("Login");

        loginButton.setOnAction(event -> {
            String username = "Student";

            LoginSuccessEvent loginEvent =
                    new LoginSuccessEvent(username);

            loginButton.fireEvent(loginEvent);
        });

        loginButton.addEventHandler(
                LoginSuccessEvent.LOGIN_SUCCESS,
                event -> {
                    messageLabel.setText(
                            "Welcome, " + event.getUsername() + "!"
                    );

                    System.out.println(
                            "Custom Event Fired: "
                            + event.getUsername()
                    );
                }
        );

        VBox root = new VBox(20, messageLabel, loginButton);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 250);

        primaryStage.setTitle("Custom Event Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
