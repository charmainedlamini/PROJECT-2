/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp;

/**
 *
 * @author musot
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

/*
 * @author Charmaine Dlamini
 */
public class WelcomeFrame extends JFrame implements ActionListener {

   // Declare GUI components
    private JLabel logoLabel;       // For displaying the logo
    private JLabel titleLabel;      // For the welcome title text
    private JButton btnLogin, btnRegister; // Login and Register buttons
    private ImageIcon originalIcon; // Store original logo image
    private JPanel mainPanel;       // Main container panel


   public WelcomeFrame() {
        super("Welcome - Room Availability Tracker"); // Set frame title
        setDefaultCloseOperation(EXIT_ON_CLOSE);     // Close app when window is closed
        setSize(500, 400);                           // Set frame size
        setLocationRelativeTo(null);                 // Center frame on screen
        getContentPane().setBackground(new Color(230, 240, 255)); // Set light blue background

        // Load logo
        originalIcon = new ImageIcon("CPUT-LOGO.png");

        // Main Panel with BoxLayout for vertical alignment
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical alignment
        mainPanel.setBackground(new Color(197, 220, 244));               // Slightly darker background
        mainPanel.setBorder(new EmptyBorder(50, 0, 50, 0));              // Add top and bottom padding
        // Logo
        logoLabel = new JLabel("", JLabel.CENTER);
         logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        logoLabel.setPreferredSize(new Dimension(100, 80));  // Set size for logo
        mainPanel.add(logoLabel);                             // Add logo to main panel


        // Add vertical space between logo and title
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Title
        titleLabel = new JLabel("Welcome to Room Availability Tracker", JLabel.CENTER);
         titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));      // Set font and size
        titleLabel.setForeground(new Color(34, 58, 136));           // Dark blue text color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);       // Center horizontally
        mainPanel.add(titleLabel);                                   // Add title to main panel


        // Add vertical space before buttons
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        // Initialize buttons panel with horizontal FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Centered buttons with spacing
        buttonPanel.setBackground(new Color(197, 220, 244));                         // Match mainPanel background


        // Login button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 16));              // Set button text style
        btnLogin.setBackground(new Color(34, 58, 136));                   // Dark blue background
        btnLogin.setForeground(Color.WHITE);                              // White text
        btnLogin.setPreferredSize(new Dimension(120, 40));                // Button size
        btnLogin.setFocusPainted(false);                                  // Remove focus border
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(34, 58, 136), 1)); // Blue border

        // Register button
        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Roboto", Font.BOLD, 16));
        btnRegister.setBackground(new Color(34, 58, 136));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setPreferredSize(new Dimension(120, 40));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createLineBorder(new Color(34, 58, 136), 1));

        // Add hover color-changing effects to both buttons
        addHoverEffect(btnLogin);
        addHoverEffect(btnRegister);

        // Add click listeners to handle Login and Register actions
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);


       // Add buttons to the button panel
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
        mainPanel.add(buttonPanel);
        // Add the main panel to the JFrame window
        add(mainPanel, BorderLayout.CENTER);

        // Animation: Pop Effect
        animateLogo();

        setVisible(true); // Make the window visible
    }

     // Creates a pop-up animation that gradually enlarges the logo
    private void animateLogo() {
        Timer timer = new Timer(50, new ActionListener() {
            int size = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                size += 10; // Increase size by 10 each tick
                if (size <= 150) {
                    // Resize the image smoothly and set it as the logo icon
                    Image scaled = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                    logoLabel.setIcon(new ImageIcon(scaled));
                } else {
                    // Stop the timer once the logo reaches full size
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start(); // Start the animation
    }

    // Add hover effect method
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(20, 108, 164));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(34, 58, 136));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            // Open Login window
            dispose(); // close welcome frame
            new LoginGUI();
        } else if (e.getSource() == btnRegister) {
            // Open Register window
            dispose();
            new RegisterGUI();
        }
    }
   
}

