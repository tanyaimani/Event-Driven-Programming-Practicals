
import javax.swing.*;
import java.awt.event.*;

public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

        JFrame frame = new JFrame("Debugging Practice");
        JButton button = new JButton("Click Me");
        JLabel label = new JLabel("Waiting for click...");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Button Clicked!");
            }
        });

        JPanel panel = new JPanel(new java.awt.FlowLayout());
        panel.add(button);
        panel.add(label);

        frame.add(panel);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    });
}