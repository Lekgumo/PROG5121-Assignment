 package com.mycompany.loginsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame("Login System");
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton signUpButton = new JButton("Sign Up");

    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JTextField phoneField = new JTextField();

    JLabel userIDLabel = new JLabel("User ID:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel phoneLabel = new JLabel("Phone (+27):");
    JLabel messageLabel = new JLabel(" ");

    HashMap<String, String> loginInfo = new HashMap<>();
    boolean isSignUpMode = false;

    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        loginInfo = loginInfoOriginal;

        
        userIDLabel.setBounds(50, 60, 75, 25);
        userPasswordLabel.setBounds(50, 100, 75, 25);
        phoneLabel.setBounds(50, 140, 100, 25);
        messageLabel.setBounds(50, 250, 300, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 14));

        
        userIDField.setBounds(150, 60, 200, 25);
        userPasswordField.setBounds(150, 100, 200, 25);
        phoneField.setBounds(150, 140, 200, 25);

        
        loginButton.setBounds(150, 180, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(250, 180, 100, 25);
        resetButton.addActionListener(this);

        signUpButton.setBounds(50, 180, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        // Add to frame
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(phoneLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(phoneField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(signUpButton);

        // Initially hide phone field
        togglePhoneField(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 350);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userID = userIDField.getText();
        String password = String.valueOf(userPasswordField.getPassword());
        String cellNumber = phoneField.getText();

        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
            phoneField.setText("");
            showMessage(" ", Color.BLACK);
            togglePhoneField(false);
            isSignUpMode = false;
        }

        if (e.getSource() == loginButton) {
            isSignUpMode = false;
            togglePhoneField(false);

            if (!checkUserName(userID)) {
                showMessage("Username must have '_' and ≤ 5 chars.", Color.RED);
                return;
            }

            if (!checkPasswordComplexity(password)) {
                showMessage("<html>Password must be 8+ chars,<br>with capital, number & symbol.</html>", Color.RED);
                return;
            }

            if (loginInfo.containsKey(userID)) {
                if (loginInfo.get(userID).equals(password)) {
                    showMessage("Login successful", Color.GREEN);
                    clearFields();
                    frame.dispose();
                    new WelcomePage(userID);
                } else {
                    showMessage("Wrong password", Color.RED);
                }
            } else {
                showMessage("Username not found", Color.RED);
            }
        }

        if (e.getSource() == signUpButton) {
            isSignUpMode = true;
            togglePhoneField(true);

            if (!checkUserName(userID)) {
                showMessage("Username must have '_' and ≤ 5 chars.", Color.RED);
                return;
            }

            if (!checkPasswordComplexity(password)) {
                showMessage("<html>Password must be 8+ chars,<br>with capital, number & symbol.</html>", Color.RED);
                return;
            }

            if (!checkCellNumber(cellNumber)) {
                showMessage("Invalid phone format. Use +27 followed by 9 digits.", Color.RED);
                return;
            }

            if (loginInfo.containsKey(userID)) {
                showMessage("Username already exists.", Color.RED);
            } else {
                loginInfo.put(userID, password);
                showMessage("Sign up successful! You can now log in.", Color.GREEN);
                clearFields();
                togglePhoneField(false);
                isSignUpMode = false;
            }
        }
    }

    // Username must contain "_" and be max 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password must be at least 8 chars, contain uppercase, digit, and symbol
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    // Phone must match South African format +27
    public boolean checkCellNumber(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }

    // Toggle phone visibility
    private void togglePhoneField(boolean visible) {
        phoneField.setVisible(visible);
        phoneLabel.setVisible(visible);
    }

    // Show message
    private void showMessage(String text, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(text);
    }

    //  Clear input fields
    private void clearFields() {
        userIDField.setText("");
        userPasswordField.setText("");
        phoneField.setText("");
    }

    
    public static void main(String[] args) {
        HashMap<String, String> sampleLoginInfo = new HashMap<>();
        sampleLoginInfo.put("kyl_1", "Lh&se@le15");
        sampleLoginInfo.put("dev_3", "DevP@ss1!");
        new LoginPage(sampleLoginInfo);
    }
}
