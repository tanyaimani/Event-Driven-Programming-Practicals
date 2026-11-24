import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventDrivenCalculator extends JFrame {

    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean newNumber = true;

    public EventDrivenCalculator() {

        setTitle("Event-Driven Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(text);
                }
            });

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleButtonClick(String value) {

        if (value.matches("[0-9]")) {

            if (newNumber || display.getText().equals("0")) {
                display.setText(value);
            } else {
                display.setText(display.getText() + value);
            }

            newNumber = false;

        } else if (value.equals("C")) {

            display.setText("0");
            firstNumber = 0;
            operator = "";
            newNumber = true;

        } else if (value.matches("[+\\-*/]")) {

            firstNumber = Double.parseDouble(display.getText());
            operator = value;
            newNumber = true;

        } else if (value.equals("=")) {

            if (operator.isEmpty()) {
                return;
            }

            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Cannot divide by zero");
                        operator = "";
                        newNumber = true;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
            newNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventDrivenCalculator());
    }
}
