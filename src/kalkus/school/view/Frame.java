package kalkus.school.view;

import kalkus.school.controller.Controller2D;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final Panel panel;
    private final Controller2D controller;

    public Frame(int width, int height) {
        init();
        panel = new Panel(width, height);
        add(panel, BorderLayout.CENTER);
        pack();

        controller = new Controller2D(panel);
    }

    public void init() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PGRF1 Kalkus");
        setVisible(true);
    }

    public Panel getPanel() {
        return panel;
    }
}
