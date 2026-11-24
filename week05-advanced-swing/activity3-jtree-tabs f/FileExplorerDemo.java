
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class FileExplorerDemo extends JFrame {

    private JTree fileTree;
    private JTabbedPane tabbedPane;

    public FileExplorerDemo() {

        setTitle("File Explorer Demo");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the root of the tree
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("My Computer");

        // Create folders
        DefaultMutableTreeNode documents =
            new DefaultMutableTreeNode("Documents");

        DefaultMutableTreeNode pictures =
            new DefaultMutableTreeNode("Pictures");

        DefaultMutableTreeNode music =
            new DefaultMutableTreeNode("Music");

        DefaultMutableTreeNode downloads =
            new DefaultMutableTreeNode("Downloads");

        // Add files to Documents
        documents.add(new DefaultMutableTreeNode("Assignment.docx"));
        documents.add(new DefaultMutableTreeNode("Report.pdf"));
        documents.add(new DefaultMutableTreeNode("Notes.txt"));

        // Add files to Pictures
        pictures.add(new DefaultMutableTreeNode("Photo1.jpg"));
        pictures.add(new DefaultMutableTreeNode("Photo2.png"));

        // Add files to Music
        music.add(new DefaultMutableTreeNode("Song1.mp3"));
        music.add(new DefaultMutableTreeNode("Song2.mp3"));

        // Add files to Downloads
        downloads.add(new DefaultMutableTreeNode("Setup.exe"));
        downloads.add(new DefaultMutableTreeNode("Document.pdf"));

        // Add folders to root
        root.add(documents);
        root.add(pictures);
        root.add(music);
        root.add(downloads);

        // Create JTree
        fileTree = new JTree(root);

        JScrollPane treeScrollPane =
            new JScrollPane(fileTree);

        // Create tabs
        tabbedPane = new JTabbedPane();

        // General tab
        JPanel generalPanel = new JPanel(new BorderLayout());
        generalPanel.add(
            new JLabel("General Information",
                SwingConstants.CENTER),
            BorderLayout.NORTH
        );

        generalPanel.add(
            new JLabel("Welcome to the File Explorer!",
                SwingConstants.CENTER),
            BorderLayout.CENTER
        );

        // Appearance tab
        JPanel appearancePanel = new JPanel(new GridLayout(3, 1));

        appearancePanel.add(
            new JLabel("Appearance Settings")
        );

        JButton lightButton = new JButton("Light Theme");
        JButton darkButton = new JButton("Dark Theme");

        lightButton.addActionListener(e -> {
            generalPanel.setBackground(Color.WHITE);
            appearancePanel.setBackground(Color.WHITE);
            JOptionPane.showMessageDialog(
                this, "Light theme selected!"
            );
        });

        darkButton.addActionListener(e -> {
            generalPanel.setBackground(Color.DARK_GRAY);
            appearancePanel.setBackground(Color.LIGHT_GRAY);
            JOptionPane.showMessageDialog(
                this, "Dark theme selected!"
            );
        });

        appearancePanel.add(lightButton);
        appearancePanel.add(darkButton);

        // About tab
        JPanel aboutPanel = new JPanel(new BorderLayout());

        JTextArea aboutText = new JTextArea(
            "FILE EXPLORER DEMO\n\n" +
            "This application demonstrates:\n" +
            "- JTree for folder navigation\n" +
            "- JTabbedPane for multiple tabs\n" +
            "- JSplitPane for dividing the window\n\n" +
            "Created using Java Swing."
        );

        aboutText.setEditable(false);
        aboutText.setMargin(new Insets(15, 15, 15, 15));

        aboutPanel.add(aboutText, BorderLayout.CENTER);

        // Add tabs
        tabbedPane.addTab("General", generalPanel);
        tabbedPane.addTab("Appearance", appearancePanel);
        tabbedPane.addTab("About", aboutPanel);

        // Create split pane
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            treeScrollPane,
            tabbedPane
        );

        splitPane.setDividerLocation(250);

        // Add split pane to window
        add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FileExplorerDemo().setVisible(true);
        });
    }
}