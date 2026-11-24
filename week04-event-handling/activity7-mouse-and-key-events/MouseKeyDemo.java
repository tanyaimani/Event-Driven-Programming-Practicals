import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseKeyDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MouseKeyDemo::createAndShow);
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Mouse and Key Events");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel instructionLabel = new JLabel(
            "Click the panel or type in the text field below.",
            SwingConstants.CENTER
        );

        JPanel mousePanel = new JPanel();
        mousePanel.setBackground(Color.CYAN);
        mousePanel.setPreferredSize(new Dimension(400, 150));

        JLabel mouseLabel = new JLabel(
            "Mouse Event Area",
            SwingConstants.CENTER
        );

        mousePanel.add(mouseLabel);

        // Mouse events
        mousePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(
                    "Mouse clicked at: " + e.getX() + ", " + e.getY()
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered the panel!");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited the panel!");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse button pressed!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse button released!");
            }
        });

        // Keyboard events
        JTextField textField = new JTextField(25);

        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(
                    "Key pressed: " + e.getKeyChar()
                );
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(
                    "Key released: " + e.getKeyChar()
                );
            }

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(
                    "Key typed: " + e.getKeyChar()
                );
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Type here:"));
        bottomPanel.add(textField);

        frame.add(instructionLabel, BorderLayout.NORTH);
        frame.add(mousePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        textField.requestFocusInWindow();
    }
}