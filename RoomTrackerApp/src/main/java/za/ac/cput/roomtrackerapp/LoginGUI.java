/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp;

/**
 *
 * @author musot
 */
import RTA.Domain.AuthenicateSys;
import java.awt.*;

import javax.swing.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {
        super("Login - Cape Peninsula University Of Technology");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 380);
        setLocationRelativeTo(null);

        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(200, 220, 250));

        ImageIcon icon = new ImageIcon("resources/cput_logo.jpg");
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(20, 10, 80, 80);
        panel.add(logoLabel);

        //Header next to the logo
        JLabel header = new JLabel("Cape Peninsula University Of Technology");
        header.setFont(new Font("Arial", Font.BOLD, 26));
        header.setBounds(120, 30, 350, 30);
        panel.add(header);

        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(200, 80, 100, 30);
        panel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setBounds(80, 130, 100, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 130, 200, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 170, 100, 25);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(200, 170, 200, 25);
        panel.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(30, 100, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(180, 220, 120, 35);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(100, 100, 100));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(180, 270, 120, 35);
        panel.add(registerButton);

        JLabel forgotLabel = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
        forgotLabel.setForeground(Color.BLUE);
        forgotLabel.setBounds(310, 200, 150, 25);
        forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        forgotLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://www.cput.ac.za/students/passwordreset"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Unable to open link.Please visit the CPUT website.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter email and password");

            } else {
                boolean success = AuthenicateSys.loginUser(email, password);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    dispose();
                    new DashboardFrame();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credintials");
                }
            }
        });
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterGUI();
        });

        add(panel);
        setVisible(true);

    }

}
