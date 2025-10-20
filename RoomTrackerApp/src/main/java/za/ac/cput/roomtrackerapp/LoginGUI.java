package za.ac.cput.roomtrackerapp;

/**
 *
 * @author Sabotseng Ndaba
 * @author Sabo
 * @author Charmaine
 */
import RTA.Domain.AuthenicateSys; // Handles authentication (login/register)
import RTA.model.LoginSession; // Handles session data for the logged-in user
import java.awt.*; // For colors, fonts, cursor, etc.
import java.io.*; // For file reading/writing (remember me functionality)
import javax.swing.*; // For GUI components

public class LoginGUI extends JFrame { // Login window class

    private static final String REMEMBER_FILE = "rememberme.txt"; // File to store saved credentials
    JTextField emailField; // Field for entering email
    JPasswordField passField; // Field for entering password
    JCheckBox rmb; // Checkbox for "Remember Me"

    public LoginGUI() {
        super("Login - Cape Peninsula University Of Technology"); // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on exit
        setSize(500, 380); // Window dimensions
        setLocationRelativeTo(null); // Center the window

        // Create a panel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Absolute positioning
        panel.setBackground(new Color(200, 220, 250)); // Light blue background

        // CPUT logo
        ImageIcon icon = new ImageIcon("resources/cput_logo.jpg");
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(20, 10, 80, 80);
        panel.add(logoLabel);

        // Header label next to logo
        JLabel header = new JLabel("Cape Peninsula University Of Technology");
        header.setFont(new Font("Arial", Font.BOLD, 26));
        header.setBounds(120, 30, 350, 30);
        panel.add(header);

        // "Login" heading
        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(200, 80, 100, 30);
        panel.add(loginLabel);

        // Email label and field
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setBounds(80, 130, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 130, 200, 25);
        panel.add(emailField);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 170, 100, 25);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(200, 170, 200, 25);
        panel.add(passField);

        // "Remember Me" checkbox
        rmb = new JCheckBox("Remember Me");
        rmb.setBounds(200, 200, 150, 25);
        panel.add(rmb);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(30, 100, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(180, 220, 120, 35);
        panel.add(loginButton);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(100, 100, 100));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(180, 270, 120, 35);
        panel.add(registerButton);

        // "Forgot Password?" link
        JLabel forgotLabel = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
        forgotLabel.setForeground(Color.BLUE);
        forgotLabel.setBounds(310, 200, 150, 25);
        forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes cursor on hover
        forgotLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Opens CPUT password reset webpage
                    Desktop.getDesktop().browse(new java.net.URI("https://www.cput.ac.za/students/passwordreset"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Unable to open link. Please visit the CPUT website.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(forgotLabel);

        LoadCredentials(); // Load saved credentials if "Remember Me" was checked

        // Action for Login button
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            // Check for empty fields
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter email and password");
            } else {
                // Call authentication system to login
                boolean success = AuthenicateSys.loginUser(email, password);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");

                    // Save email to session for dashboard or further use
                    LoginSession.setEmail(email);

                    // Save credentials if "Remember Me" is checked
                    if (rmb.isSelected()) {
                        saveCredentials(email, password);
                    } else {
                        clearCredentials(); // Remove saved credentials if unchecked
                    }

                    dispose(); // Close login window
                    new DashboardFrame(); // Open dashboard
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            }
        });

        // Action for Register button
        registerButton.addActionListener(e -> {
            dispose(); // Close login window
            new RegisterGUI(); // Open registration window
        });

        add(panel); // Add panel to JFrame
        setVisible(true); // Make window visible
    }

    // Saves email and password to a file for "Remember Me"
    private void saveCredentials(String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REMEMBER_FILE))) {
            writer.write(email + "\n");
            writer.write(password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads credentials from file if available
    private void LoadCredentials() {
        File file = new File(REMEMBER_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String email = reader.readLine();
                String password = reader.readLine();
                if (email != null) emailField.setText(email);
                if (password != null) passField.setText(password);
                rmb.setSelected(true); // Check "Remember Me" if credentials loaded
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Deletes the saved credentials file
    public void clearCredentials() {
        File file = new File(REMEMBER_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}



