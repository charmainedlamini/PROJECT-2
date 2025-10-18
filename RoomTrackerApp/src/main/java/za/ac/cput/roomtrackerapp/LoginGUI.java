/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp;

/**
 *
 * @author musot
 * @author Sabo
 * @author Charmaine
 */
import RTA.Domain.AuthenicateSys;
import RTA.model.LoginSession;
import java.awt.*;
import java.io.*;
import javax.swing.*;

import javax.swing.*;

public class LoginGUI extends JFrame {
     private static final String REMEMBER_FILE = "rememberme.txt";
    JTextField emailField;
    JPasswordField passField;
    JCheckBox rmb;

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

        emailField = new JTextField();
        emailField.setBounds(200, 130, 200, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 170, 100, 25);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(200, 170, 200, 25);
        panel.add(passField);

        JCheckBox rmb = new JCheckBox("Remember Me");
        rmb.setBounds(200, 200, 150, 25);
        panel.add(rmb);
        
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
        panel.add(forgtLabel);
        LoadCredentials();
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter email and password");

            } else {
                boolean success = AuthenicateSys.loginUser(email, password);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                     //Charmaine - i added this example of using a session to store user data which is email so that after the user login in with email
                     //I will use that email to make it visible in the dashboard
                     // Store the logged-in email in LoginSession
                    LoginSession.setEmail(email);
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
    private void saveCredentials(String email, String password) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(REMEMBER_FILE))) {
            writer.write(email + "\n");
            writer.write(password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadCredentials() {
        File file = new File(REMEMBER_FILE);
        if (file.exists()) {
            try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String email = reader.readLine();
                String password = reader.readLine();
                if (email != null) {
                    emailField.setText(email);
                }
                if (password != null) {
                    passField.setText(password);
                }
                rmb.setSelected(true);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void clearCredentials() {
        File file = new File(REMEMBER_FILE);
        if (file.exists()) {
            file.delete();
        }

    }

}


