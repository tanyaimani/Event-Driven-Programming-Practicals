
import javax.swing.*;
import java.awt.*;

public class StudentRegistrationSystem extends JFrame {

    private JTextField nameField;
    private JComboBox<String> courseBox;
    private DefaultListModel<String> studentList;

    public StudentRegistrationSystem() {

        setTitle("Student Registration System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel title = new JLabel("STUDENT REGISTRATION");
        JLabel nameLabel = new JLabel("Student Name:");
        JLabel courseLabel = new JLabel("Select Course:");

        nameField = new JTextField();

        String[] courses = {
            "Computer Science",
            "Information Technology",
            "Software Engineering",
            "Business Information Technology"
        };

        courseBox = new JComboBox<>(courses);

        JButton addButton = new JButton("Add Student");
        JButton clearButton = new JButton("Clear");

        studentList = new DefaultListModel<>();
        JList<String> list = new JList<>(studentList);

        addButton.addActionListener(e -> registerStudent());

        clearButton.addActionListener(e -> {
            nameField.setText("");
            courseBox.setSelectedIndex(0);
        });

        panel.add(title);
        panel.add(new JLabel(""));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(courseLabel);
        panel.add(courseBox);
        panel.add(addButton);
        panel.add(clearButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        setVisible(true);
    }

    private void registerStudent() {

        String name = nameField.getText().trim();
        String course = (String) courseBox.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the student's name!"
            );
            return;
        }

        studentList.addElement(name + " - " + course);

        JOptionPane.showMessageDialog(
            this,
            "Student registered successfully!"
        );

        nameField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new StudentRegistrationSystem()
        );
    }
}
