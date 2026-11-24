package com.mycompany.programmaticdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardCapstoneController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private void handleViewStudents() {
        welcomeLabel.setText("Welcome to the Student Management System!");
    }

    @FXML
    private void handleHome() {
        welcomeLabel.setText("Welcome to My Dashboard!");
    }

    @FXML
    private void handleStudents() {
        welcomeLabel.setText("You selected Students.");
    }

    @FXML
    private void handleSettings() {
        welcomeLabel.setText("You selected Settings.");
    }
}
