import javax.swing.*;
import java.awt.*;

public class EventSourceDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventSourceDemo::createAndShow);
    }

    private static void createAndShow() {

        JFrame frame = new JFrame("Event Source Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        button1.addActionListener(e ->
            System.out.println("Button 1 was clicked!")
        );

        button2.addActionListener(e ->
            System.out.println("Button 2 was clicked!")
        );

        frame.add(button1);
        frame.add(button2);

        frame.setVisible(true);
    }
}
