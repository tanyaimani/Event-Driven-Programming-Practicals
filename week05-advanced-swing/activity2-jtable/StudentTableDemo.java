
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class StudentTableDemo extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentTableDemo() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table columns
        String[] columns = {"Student Name", "GPA"};

        // Student data
        Object[][] data = {
            {"Alice", 3.8},
            {"Brian", 2.5},
            {"Carol", 3.6},
            {"David", 2.8},
            {"Emma", 3.9},
            {"Frank", 2.9}
        };

        // Create table model
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        // Create JTable
        studentTable = new JTable(tableModel);

        // Custom renderer for GPA column
        studentTable.getColumnModel().getColumn(1)
            .setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                    Component cell = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                    );

                    if (!isSelected && value instanceof Number) {
                        double gpa = ((Number) value).doubleValue();

                        if (gpa < 3.0) {
                            cell.setBackground(Color.RED);
                            cell.setForeground(Color.WHITE);
                        } else {
                            cell.setBackground(Color.WHITE);
                            cell.setForeground(Color.BLACK);
                        }
                    }

                    return cell;
                }
            });

        // Add Student button
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            tableModel.addRow(new Object[]{"New Student", 0.0});
        });

        // Delete Student button
        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();

            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(
                    this, "Please select a student to delete."
                );
            }
        });

        // Clear Table button
        JButton clearButton = new JButton("Clear Table");
        clearButton.addActionListener(e -> {
            tableModel.setRowCount(0);
        });

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Add components to window
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentTableDemo().setVisible(true);
        });
    }
}
