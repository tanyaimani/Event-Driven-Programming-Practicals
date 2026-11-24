import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class ListenerTypesDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListenerTypesDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("ItemListener and DocumentListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        // ItemListener example
        JCheckBox checkBox = new JCheckBox("I agree");

        checkBox.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                System.out.println("Checkbox selected!");
            } else {
                System.out.println("Checkbox deselected!");
            }
        });

        // DocumentListener example
        JTextField textField = new JTextField(20);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("Text inserted: " + textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("Text removed: " + textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Text changed!");
            }
        });

        frame.add(checkBox);
        frame.add(new JLabel("Type something:"));
        frame.add(textField);

        frame.setVisible(true);
    }
}