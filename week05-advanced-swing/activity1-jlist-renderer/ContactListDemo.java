
import javax.swing.*;
import java.awt.*;

public class ContactListDemo extends JFrame {

    private JList<String> contactList;
    private JLabel selectedLabel;

    public ContactListDemo() {
        setTitle("Contact List Demo");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] contacts = {
            "Alice - 0712345678",
            "Brian - 0723456789",
            "Carol - 0734567890",
            "David - 0745678901",
            "Emma - 0756789012"
        };

        contactList = new JList<>(contacts);
        contactList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        // Custom renderer for each contact
        contactList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus
                );

                label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

                if (!isSelected) {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }

                return label;
            }
        });

        selectedLabel = new JLabel("Select a contact");
        selectedLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Listen for contact selection
        contactList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                String selected = contactList.getSelectedValue();

                if (selected != null) {
                    selectedLabel.setText("Selected: " + selected);
                }
            }
        });

        add(new JScrollPane(contactList), BorderLayout.CENTER);
        add(selectedLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ContactListDemo().setVisible(true);
        });
    }
}