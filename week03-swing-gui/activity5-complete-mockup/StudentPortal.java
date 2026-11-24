
import javax.swing.*;
import java.awt.*;

public class StudentPortal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> createAndShow());
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Student Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(5, 5));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(35, 55, 90));
        header.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("UNIVERSITY STUDENT PORTAL");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel welcome = new JLabel("Welcome, Student!");
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Arial", Font.PLAIN, 14));

        header.add(title, BorderLayout.WEST);
        header.add(welcome, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        // SIDEBAR
        JPanel sidebar = new JPanel(new GridLayout(6, 1, 5, 5));
        sidebar.setBackground(new Color(235, 240, 248));
        sidebar.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10));

        sidebar.add(new JButton("Dashboard"));
        sidebar.add(new JButton("My Profile"));
        sidebar.add(new JButton("My Courses"));
        sidebar.add(new JButton("Results"));
        sidebar.add(new JButton("Notifications"));
        sidebar.add(new JButton("Logout"));

        frame.add(sidebar, BorderLayout.WEST);

        // MAIN CONTENT
        JPanel mainContent = new JPanel(new BorderLayout(10, 10));
        mainContent.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel dashboardTitle = new JLabel("Dashboard Overview");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 20));

        mainContent.add(dashboardTitle, BorderLayout.NORTH);

        // INFORMATION CARDS
        JPanel cards = new JPanel(new GridLayout(2, 2, 15, 15));

        cards.add(createCard("STUDENT ID", "STU2026001"));
        cards.add(createCard("PROGRAM", "Computer Science"));
        cards.add(createCard("CURRENT SEMESTER", "Semester 2"));
        cards.add(createCard("ACADEMIC STATUS", "Active"));

        mainContent.add(cards, BorderLayout.CENTER);

        // NOTIFICATIONS
        JPanel notifications = new JPanel();
        notifications.setLayout(
                new BoxLayout(notifications, BoxLayout.Y_AXIS));

        notifications.setBorder(
                BorderFactory.createTitledBorder("Recent Notifications"));

        notifications.add(new JLabel("• Registration is open."));
        notifications.add(Box.createVerticalStrut(8));
        notifications.add(new JLabel("• Assignment submission is due soon."));
        notifications.add(Box.createVerticalStrut(8));
        notifications.add(new JLabel("• Semester timetable is available."));

        mainContent.add(notifications, BorderLayout.SOUTH);

        frame.add(mainContent, BorderLayout.CENTER);

        // STATUS BAR
        JLabel status = new JLabel("Student Portal | Ready");
        status.setBorder(
                BorderFactory.createEmptyBorder(8, 12, 8, 12));

        frame.add(status, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createCard(String heading, String value) {

        JPanel card = new JPanel(new BorderLayout(5, 10));

        card.setBackground(new Color(240, 245, 255));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                )
        );

        JLabel headingLabel = new JLabel(heading);
        headingLabel.setFont(
                new Font("Arial", Font.BOLD, 13));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(
                new Font("Arial", Font.PLAIN, 16));

        card.add(headingLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}
