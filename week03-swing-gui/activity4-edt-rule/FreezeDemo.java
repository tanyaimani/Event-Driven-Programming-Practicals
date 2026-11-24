
import javax.swing.*;
import java.awt.*;

public class FreezeDemo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Freeze Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel label = new JLabel("Click the button to start.");
            JButton button = new JButton("Start Long Task");

            button.addActionListener(e -> {

                label.setText("Working... Please wait!");

                // BAD PRACTICE: This blocks the Event Dispatch Thread.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                label.setText("Task completed!");
            });

            frame.add(label);
            frame.add(button);

            frame.setVisible(true);
        });
    }
}
