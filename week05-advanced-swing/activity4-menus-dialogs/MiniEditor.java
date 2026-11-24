
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MiniEditor extends JFrame {

    private JTextArea textArea;
    private JLabel statusLabel;

    public MiniEditor() {

        setTitle("Mini Text Editor");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem wordCountItem = new JMenuItem("Word Count");

        // File menu actions
        newItem.addActionListener(e -> {
            textArea.setText("");
            statusLabel.setText("New document created");
        });

        openItem.addActionListener(e -> openFile());

        saveItem.addActionListener(e -> saveFile());

        exitItem.addActionListener(e -> {
            dispose();
        });

        // Edit menu actions
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        wordCountItem.addActionListener(e -> showWordCount());

        // Add items to menus
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(wordCountItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        // Create toolbar
        JToolBar toolBar = new JToolBar();

        JButton newButton = new JButton("New");
        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");
        JButton countButton = new JButton("Word Count");

        newButton.addActionListener(e -> newItem.doClick());
        openButton.addActionListener(e -> openFile());
        saveButton.addActionListener(e -> saveFile());
        countButton.addActionListener(e -> showWordCount());

        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(countButton);

        // Status bar
        statusLabel = new JLabel("Ready");

        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    // Open a text file
    private void openFile() {

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader =
                    new BufferedReader(new FileReader(file))) {

                textArea.read(reader, null);
                statusLabel.setText("Opened: " + file.getName());

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Error opening file: " + ex.getMessage()
                );
            }
        }
    }

    // Save a text file
    private void saveFile() {

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file))) {

                textArea.write(writer);
                statusLabel.setText("Saved: " + file.getName());

                JOptionPane.showMessageDialog(
                    this, "File saved successfully!"
                );

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Error saving file: " + ex.getMessage()
                );
            }
        }
    }

    // Count words
    private void showWordCount() {

        String text = textArea.getText().trim();

        int count = text.isEmpty()
            ? 0
            : text.split("\\s+").length;

        JOptionPane.showMessageDialog(
            this,
            "Word Count: " + count,
            "Document Statistics",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MiniEditor().setVisible(true);
        });
    }
}