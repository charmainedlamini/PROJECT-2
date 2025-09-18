/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp;

import RTA.Domain.AuthenicateSys;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author musot
 */
public class RegisterGUI extends JFrame {

    public RegisterGUI() {

        super("Login - Cape Peninsula University Of Technology");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(
                new Color(200, 220, 250));

        ImageIcon icon = new ImageIcon("resources/cput_logo.jpg");

        System.out.println(RegisterGUI.class.getResource("resources/cput_logo.jpg"));
        JLabel logoLabel = new JLabel(icon);

        logoLabel.setBounds(
                20, 10, 80, 80);
        panel.add(logoLabel);

        JLabel header = new JLabel("Cape Peninsula University of Technology");

        header.setFont(
                new Font("Arial", Font.BOLD, 26));
        header.setBounds(
                120, 30, 350, 30);
        panel.add(header);

        JLabel registerLabel = new JLabel("Register", SwingConstants.CENTER);

        registerLabel.setFont(
                new Font("Arial", Font.BOLD, 20));
        registerLabel.setBounds(
                200, 90, 120, 30);
        panel.add(registerLabel);

        JLabel nameLabel = new JLabel("Full name and surname");

        nameLabel.setBounds(
                80, 140, 100, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();

        nameField.setBounds(
                200, 140, 200, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email Address");

        emailLabel.setBounds(
                80, 180, 100, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();

        emailField.setBounds(
                200, 180, 200, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password");

        passLabel.setBounds(
                80, 220, 100, 25);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();

        passField.setBounds(
                200, 220, 200, 25);
        panel.add(passField);

        JLabel confirmLabel = new JLabel("Confirm Password:");

        confirmLabel.setBounds(
                80, 260, 120, 25);
        panel.add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();

        confirmField.setBounds(
                200, 260, 200, 25);
        panel.add(confirmField);

        JButton registerButton = new JButton("Register");

        registerButton.setBackground(
                new Color(30, 100, 200));
        registerButton.setForeground(Color.WHITE);

        registerButton.setBounds(100, 320, 120, 35);
        panel.add(registerButton);

        registerButton.addActionListener(e
                -> {
            String fullName = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());
            
            
            String studentID = JOptionPane.showInputDialog(this, "Enter student ID:");
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            } else{
                AuthenicateSys.registerUser(fullName, password, confirmPassword, studentID, email);
                dispose();
               new DashboardFrame();
            }
        }
        );

        JButton backButton = new JButton("Back");

        backButton.setBackground(
                new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);

        backButton.setBounds(
                250, 320, 120, 35);
        panel.add(backButton);

        backButton.addActionListener(e
                -> {
            dispose();
            new LoginGUI();
        }
        );

        add(panel);

        setVisible(
                true);
    }

}
