import javax.swing.*;
import java.awt.*;

public class CounterApp {

    private static int count = 0;
    private static JLabel countLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CounterApp::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Counter Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton incrementButton = new JButton("Increment");
        JButton decrementButton = new JButton("Decrement");
        JButton resetButton = new JButton("Reset");

        incrementButton.addActionListener(e -> {
            count++;
            updateCount();
        });

        decrementButton.addActionListener(e -> {
            count--;
            updateCount();
        });

        resetButton.addActionListener(e -> {
            count = 0;
            updateCount();
        });

        frame.add(countLabel);
        frame.add(incrementButton);
        frame.add(decrementButton);
        frame.add(resetButton);

        frame.setVisible(true);
    }

    private static void updateCount() {
        countLabel.setText("Count: " + count);
    }
}