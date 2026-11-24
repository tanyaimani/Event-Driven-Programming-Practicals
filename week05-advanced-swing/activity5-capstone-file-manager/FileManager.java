
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

public class FileManager extends JFrame {

    private JTree fileTree;
    private JTable fileTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private File currentDirectory;

    public FileManager() {

        setTitle("Java File Manager");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentDirectory = new File(System.getProperty("user.home"));

        // Create the folder tree
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode(currentDirectory);

        File[] folders = currentDirectory.listFiles(File::isDirectory);

        if (folders != null) {
            for (File folder : folders) {
                root.add(new DefaultMutableTreeNode(folder));
            }
        }

        fileTree = new JTree(root);
        fileTree.setRootVisible(true);

        fileTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)
                    fileTree.getLastSelectedPathComponent();

            if (node != null) {
                File selectedFile = (File) node.getUserObject();

                if (selectedFile.isDirectory()) {
                    currentDirectory = selectedFile;
                    loadFiles(currentDirectory);
                }
            }
        });

        JScrollPane treeScrollPane = new JScrollPane(fileTree);

        // Create file table
        String[] columns = {"Name", "Type", "Size"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        fileTable = new JTable(tableModel);
        fileTable.setRowHeight(25);

        JScrollPane tableScrollPane =
            new JScrollPane(fileTable);

        // Split pane
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            treeScrollPane,
            tableScrollPane
        );

        splitPane.setDividerLocation(280);

        // Toolbar
        JToolBar toolBar = new JToolBar();

        JButton upButton = new JButton("Up");
        JButton refreshButton = new JButton("Refresh");
        JButton chooseButton = new JButton("Choose Folder");

        upButton.addActionListener(e -> goUp());
        refreshButton.addActionListener(e ->
            loadFiles(currentDirectory));
        chooseButton.addActionListener(e -> chooseFolder());

        toolBar.add(upButton);
        toolBar.add(refreshButton);
        toolBar.add(chooseButton);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu viewMenu = new JMenu("View");

        JMenuItem chooseItem = new JMenuItem("Choose Folder");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem refreshItem = new JMenuItem("Refresh");

        chooseItem.addActionListener(e -> chooseFolder());
        exitItem.addActionListener(e -> dispose());
        refreshItem.addActionListener(e ->
            loadFiles(currentDirectory));

        fileMenu.add(chooseItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        viewMenu.add(refreshItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);

        // Status bar
        statusLabel = new JLabel("Ready");

        // Double-click folders in the table
        fileTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int row = fileTable.getSelectedRow();

                    if (row != -1) {
                        String name = tableModel.getValueAt(
                            row, 0
                        ).toString();

                        File selected = new File(
                            currentDirectory, name
                        );

                        if (selected.isDirectory()) {
                            currentDirectory = selected;
                            loadFiles(currentDirectory);
                        }
                    }
                }
            }
        });

        // Add components
        add(toolBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Load initial files
        loadFiles(currentDirectory);
    }

    // Display files and folders in the table
    private void loadFiles(File directory) {

        tableModel.setRowCount(0);

        File[] files = directory.listFiles();

        if (files == null) {
            statusLabel.setText(
                "Cannot access folder: " + directory.getPath()
            );
            return;
        }

        Arrays.sort(files, Comparator
            .comparing(File::isFile)
            .thenComparing(File::getName,
                String.CASE_INSENSITIVE_ORDER));

        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        for (File file : files) {

            String name = file.getName();

            String type = file.isDirectory()
                ? "Folder"
                : "File";

            String size = file.isDirectory()
                ? "-"
                : formatter.format(file.length() / 1024.0) + " KB";

            tableModel.addRow(new Object[]{
                name, type, size
            });
        }

        statusLabel.setText(
            "Location: " + directory.getAbsolutePath()
            + " | Items: " + files.length
        );
    }

    // Move to parent folder
    private void goUp() {

        File parent = currentDirectory.getParentFile();

        if (parent != null) {
            currentDirectory = parent;
            loadFiles(currentDirectory);
        } else {
            JOptionPane.showMessageDialog(
                this, "You are already at the top level."
            );
        }
    }

    // Choose a folder using JFileChooser
    private void chooseFolder() {

        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(
            JFileChooser.DIRECTORIES_ONLY
        );

        chooser.setCurrentDirectory(currentDirectory);

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            currentDirectory = chooser.getSelectedFile();
            loadFiles(currentDirectory);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FileManager().setVisible(true);
        });
    }
}