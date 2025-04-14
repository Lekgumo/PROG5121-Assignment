 package com.mycompany.loginsystem;

import javax.swing.*;

public class WelcomePage {
    JFrame frame = new JFrame("Welcome");

    public WelcomePage(String userID) {
        JLabel welcomeLabel = new JLabel("Welcome, " + userID + "!");
        welcomeLabel.setBounds(20, 20, 300, 25);

        frame.add(welcomeLabel);
        frame.setSize(300, 150);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
