package kalkus.school;

import kalkus.school.view.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Frame(800, 800).getPanel().clear());
    }
}