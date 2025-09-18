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

    private JLabel logoLabel;
    private JLabel titleLabel;
    private JButton btnLogin, btnRegister;
    private ImageIcon originalIcon;
    private JPanel mainPanel;

    public WelcomeFrame() {
        super("Welcome - Room Availability Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255)); // Set light blue background

        // Load logo
        originalIcon = new ImageIcon("CPUT-LOGO.png");

        // Main Panel with BoxLayout for vertical alignment
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(197, 220, 244));
        mainPanel.setBorder(new EmptyBorder(50, 0, 50, 0)); // Add top and bottom padding

        // Logo
        logoLabel = new JLabel("", JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setPreferredSize(new Dimension(100, 80)); // Set appropriate width and height
        mainPanel.add(logoLabel);

        // Add vertical space between logo and title
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Title
        titleLabel = new JLabel("Welcome to Room Availability Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24)); // Increased font size
        titleLabel.setForeground(new Color(34, 58, 136)); // Dark blue color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Add vertical space before buttons
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        // Buttons Panel with FlowLayout for horizontal alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Spacing between buttons
        buttonPanel.setBackground(new Color(197, 220, 244));

        // Login button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 16));
        btnLogin.setBackground(new Color(34, 58, 136));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setFocusPainted(false); // Removes the focus border
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(34, 58, 136), 1)); // Add border

        // Register button
        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Roboto", Font.BOLD, 16));
        btnRegister.setBackground(new Color(34, 58, 136));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setPreferredSize(new Dimension(120, 40));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createLineBorder(new Color(34, 58, 136), 1));

        // Hover effects
        addHoverEffect(btnLogin);
        addHoverEffect(btnRegister);
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Animation: Pop Effect
        animateLogo();

        setVisible(true);
    }

    // Updated animateLogo method to use the mainPanel
    private void animateLogo() {
        Timer timer = new Timer(50, new ActionListener() {
            int size = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                size += 10;
                if (size <= 150) {
                    Image scaled = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                    logoLabel.setIcon(new ImageIcon(scaled));
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
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
