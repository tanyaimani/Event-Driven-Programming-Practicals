
import javax.swing.*;

public class FirstWindow {

    public static void main(String[] args) {

        // Run Swing code on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            createAndShowWindow();
        });
    }

    private static void createAndShowWindow() {

        // Create the main window.
        JFrame frame = new JFrame("My First Swing App");

        // Close the application when X is clicked.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set window size.
        frame.setSize(800, 600);

        // Center the window.
        frame.setLocationRelativeTo(null);

        // Display the window.
        frame.setVisible(true);

        // Confirm the thread running the GUI.
        System.out.println("Window created on thread: "
                + Thread.currentThread().getName());
    }
}
