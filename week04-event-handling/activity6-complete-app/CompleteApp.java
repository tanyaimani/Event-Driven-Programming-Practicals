import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class CompleteApp {

    private static JTextField nameField;
    private static JTextField emailField;
    private static JComboBox<String> courseBox;
    private static JCheckBox agreeBox;
    private static JLabel statusLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CompleteApp::createAndShow);
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Complete Event Handling App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        emailField = new JTextField();

        String[] courses = {
            "Java Programming",
            "Python Programming",
            "Web Development",
            "Database Management"
        };

        courseBox = new JComboBox<>(courses);
        agreeBox = new JCheckBox("I agree to register");

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        statusLabel = new JLabel("Please enter your details.");

        // DocumentListener: monitor name changes
        nameField.getDocument().addDocumentListener(
            new DocumentListener() {

                public void insertUpdate(DocumentEvent e) {
                    updateStatus();
                }

                public void removeUpdate(DocumentEvent e) {
                    updateStatus();
                }

                public void changedUpdate(DocumentEvent e) {
                    updateStatus();
                }
            }
        );

        // ItemListener: monitor checkbox changes
        agreeBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Agreement selected!");
            } else {
                System.out.println("Agreement deselected!");
            }
        });

        // ActionListener: submit button
        submitButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String course = (String) courseBox.getSelectedItem();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Please fill in all fields."
                );
            } else if (!agreeBox.isSelected()) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Please accept the agreement."
                );
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Registration Successful!\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Course: " + course
                );

                statusLabel.setText("Registration successful!");
            }
        });

        // ActionListener: clear button
        clearButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            courseBox.setSelectedIndex(0);
            agreeBox.setSelected(false);
            statusLabel.setText("Please enter your details.");
        });

        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Select Course:"));
        panel.add(courseBox);

        panel.add(agreeBox);
        panel.add(new JLabel(""));

        panel.add(submitButton);
        panel.add(clearButton);

        panel.add(new JLabel("Application Status:"));
        panel.add(statusLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void updateStatus() {
        statusLabel.setText(
            "Name entered: " + nameField.getText()
        );
    }
}