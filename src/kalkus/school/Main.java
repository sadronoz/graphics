package kalkus.school;

import kalkus.school.view.Frame;
import kalkus.school.view.Panel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Frame(800, 800).getPanel().clear());

        /*SwingUtilities.invokeLater(() -> {
            // Create a basic frame and panel for testing
            JFrame frame = new JFrame("Test");
            Panel panel = new Panel(800, 800);
            frame.add(panel);
            panel.setPreferredSize(new Dimension(800, 800));
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            panel.clear();  // Call the clear method
        });*/
    }
}