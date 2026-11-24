
import javax.swing.*;
import java.awt.*;

public class FixedDemo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Fixed Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel label = new JLabel("Click the button to start.");
            JButton button = new JButton("Start Long Task");

            button.addActionListener(e -> {

                button.setEnabled(false);
                label.setText("Working... Please wait!");

                // Run the long task in a background thread.
                Thread worker = new Thread(() -> {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    // Update Swing components safely on the EDT.
                    SwingUtilities.invokeLater(() -> {
                        label.setText("Task completed!");
                        button.setEnabled(true);
                    });
                });

                worker.start();
            });

            frame.add(label);
            frame.add(button);

            frame.setVisible(true);
        });
    }
}
