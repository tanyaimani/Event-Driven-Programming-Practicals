import javax.swing.*;
import java.awt.*;

public class InteractiveForm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InteractiveForm::createAndShow);
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Interactive Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel courseLabel = new JLabel("Choose Course:");
        String[] courses = {
            "Java Programming",
            "Python Programming",
            "Web Development",
            "Database Management"
        };

        JComboBox<String> courseBox = new JComboBox<>(courses);

        JCheckBox agreeBox = new JCheckBox("I agree to the information provided");

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

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
                    "Please agree before submitting."
                );
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Registration Successful!\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Course: " + course
                );
            }
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            courseBox.setSelectedIndex(0);
            agreeBox.setSelected(false);
        });

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(courseLabel);
        panel.add(courseBox);

        panel.add(agreeBox);
        panel.add(new JLabel(""));

        panel.add(submitButton);
        panel.add(clearButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}