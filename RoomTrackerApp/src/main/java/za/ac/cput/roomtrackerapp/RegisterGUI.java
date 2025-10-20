/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp; // Package name for this class

import RTA.Domain.AuthenicateSys; // Importing the class responsible for user authentication and registration
import java.awt.*; // Importing AWT classes for GUI elements like Color, Font, etc.
import javax.swing.*; // Importing Swing components for building the GUI

/**
 *
 * @author Sabotseng Ndaba
 */
public class RegisterGUI extends JFrame { // The class extends JFrame to create a window for the registration form

    // Constructor to initialize and set up the registration GUI
    public RegisterGUI() {

        super("Login - Cape Peninsula University Of Technology"); // Sets the title of the window

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the program when the window is closed
        setSize(500, 450); // Sets the size of the window (width: 500px, height: 450px)
        setLocationRelativeTo(null); // Centers the window on the screen

        JPanel panel = new JPanel(); // Creates a panel to hold all the components
        panel.setLayout(null); // Disables layout manager (using absolute positioning)
        panel.setBackground(new Color(200, 220, 250)); // Sets background color to a light blue shade

        // Loads an image icon for the CPUT logo
        ImageIcon icon = new ImageIcon("resources/cput_logo.jpg");

        // Prints out the resource path for debugging (optional)
        System.out.println(RegisterGUI.class.getResource("resources/cput_logo.jpg"));

        JLabel logoLabel = new JLabel(icon); // Creates a label to hold the CPUT logo
        logoLabel.setBounds(20, 10, 80, 80); // Positions and sizes the logo on the panel
        panel.add(logoLabel); // Adds the logo to the panel

        // Creates a header label displaying the university name
        JLabel header = new JLabel("Cape Peninsula University of Technology");
        header.setFont(new Font("Arial", Font.BOLD, 26)); // Sets font style and size
        header.setBounds(120, 30, 350, 30); // Positions the header on the panel
        panel.add(header); // Adds the header to the panel

        // Creates a "Register" heading label
        JLabel registerLabel = new JLabel("Register", SwingConstants.CENTER);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Sets font style and size
        registerLabel.setBounds(200, 90, 120, 30); // Positions the label
        panel.add(registerLabel); // Adds it to the panel

        // Label for the full name input
        JLabel nameLabel = new JLabel("Full name and surname");
        nameLabel.setBounds(80, 140, 150, 25); // Sets position and size
        panel.add(nameLabel); // Adds label to panel

        // Text field where the user enters their full name
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 140, 200, 25); // Sets position and size
        panel.add(nameField); // Adds the text field to the panel

        // Label for email input
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setBounds(80, 180, 100, 25); // Sets position and size
        panel.add(emailLabel); // Adds label to panel

        // Text field for the user’s email address
        JTextField emailField = new JTextField();
        emailField.setBounds(200, 180, 200, 25); // Sets position and size
        panel.add(emailField); // Adds the text field to the panel

        // Label for password input
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(80, 220, 100, 25); // Sets position and size
        panel.add(passLabel); // Adds label to panel

        // Password field to hide password input
        JPasswordField passField = new JPasswordField();
        passField.setBounds(200, 220, 200, 25); // Sets position and size
        panel.add(passField); // Adds password field to panel

        // Label for confirming password
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(80, 260, 120, 25); // Sets position and size
        panel.add(confirmLabel); // Adds label to panel

        // Password field to confirm password input
        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(200, 260, 200, 25); // Sets position and size
        panel.add(confirmField); // Adds field to panel

        // Button for registering the user
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(30, 100, 200)); // Sets button color (blue)
        registerButton.setForeground(Color.WHITE); // Sets text color to white
        registerButton.setBounds(100, 320, 120, 35); // Sets button size and position
        panel.add(registerButton); // Adds button to panel

        // Action listener for the "Register" button
        registerButton.addActionListener(e -> {
            String fullName = nameField.getText(); // Gets text from full name field
            String email = emailField.getText(); // Gets text from email field
            String password = new String(passField.getPassword()); // Gets password as string
            String confirmPassword = new String(confirmField.getPassword()); // Gets confirmation password

            // Prompts user to enter their student ID
            String studentID = JOptionPane.showInputDialog(this, "Enter student ID:");

            // Checks if any field is empty
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields."); // Shows error message
            } else {
                // Calls the authentication system to register the user
                AuthenicateSys.registerUser(fullName, password, confirmPassword, studentID, email);
                dispose(); // Closes the registration window
                new DashboardFrame(); // Opens the Dashboard page after registration
            }
        });

        // Button to go back to the login screen
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(150, 150, 150)); // Sets button color (grey)
        backButton.setForeground(Color.WHITE); // Sets text color to white
        backButton.setBounds(250, 320, 120, 35); // Sets button size and position
        panel.add(backButton); // Adds button to panel

        // Action listener for the "Back" button
        backButton.addActionListener(e -> {
            dispose(); // Closes current window
            new LoginGUI(); // Opens the Login GUI
        });

        add(panel); // Adds the entire panel to the JFrame
        setVisible(true); // Makes the window visible
    }
}
