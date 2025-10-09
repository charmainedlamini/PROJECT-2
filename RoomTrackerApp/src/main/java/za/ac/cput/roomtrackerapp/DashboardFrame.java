/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.roomtrackerapp;

/**
 *
 * @author musot
 * @author Charmaine Dlamini
 */
import RTA.Process.CheckRoomAvailability;
import RTA.Process.CurrentRoomFrame;
import RTA.Process.RoomApplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class DashboardFrame extends JFrame {

    // Declare components first
    private JPanel headerPanel, logoAndTitlePanel, titlePanel, emailAndButtonPanel, dashboardPanel, cardsPanel;
    private JLabel logoLabel, line1, line2, emailLabel, dashboardTitle;
    private JButton btnLogout;
    private JFrame frame;

    // Constructor
    public DashboardFrame() {
        super("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(197, 220, 244));
        setLocationRelativeTo(null);

        // Initialize header panel
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(197, 220, 244));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize logo and title panel
        logoAndTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoAndTitlePanel.setBackground(new Color(197, 220, 244));

        // Initialize logo
        ImageIcon logoIcon = new ImageIcon("CPUT-LOGO2.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoAndTitlePanel.add(logoLabel);

        // Initialize title panel (two lines)
        titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(197, 220, 244));

        line1 = new JLabel("Cape Peninsula", JLabel.LEFT);
        line1.setFont(new Font("Roboto", Font.BOLD, 22));
        line1.setForeground(new Color(34, 58, 136));

        line2 = new JLabel("University of Technology", JLabel.LEFT);
        line2.setFont(new Font("Roboto", Font.BOLD, 22));
        line2.setForeground(new Color(34, 58, 136));

        titlePanel.add(line1);
        titlePanel.add(line2);
        logoAndTitlePanel.add(titlePanel);
        headerPanel.add(logoAndTitlePanel, BorderLayout.WEST);

        // Initialize email & logout panel
        emailAndButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        emailAndButtonPanel.setBackground(new Color(197, 220, 244));

        emailLabel = new JLabel("example@example.com");
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        emailAndButtonPanel.add(emailLabel);

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Roboto", Font.PLAIN, 14));
        btnLogout.setBackground(new Color(34, 58, 136));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Hover effect
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogout.setBackground(new Color(20, 108, 164));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogout.setBackground(new Color(34, 58, 136));
            }
        });

        // Add an action listener to the button using an anonymous class
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog
                int confirmed = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                // If the user confirmed, show a message dialog and then dispose of the frame
                if (confirmed == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "You have successfully logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the current frame
                }
            }
        });

        emailAndButtonPanel.add(btnLogout);
        headerPanel.add(emailAndButtonPanel, BorderLayout.EAST);

        // Initialize dashboard panel
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(new Color(197, 220, 244));
        dashboardPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setFont(new Font("Roboto", Font.BOLD, 24));
        dashboardTitle.setForeground(Color.BLACK);
        dashboardPanel.add(dashboardTitle, BorderLayout.NORTH);

        // Initialize cards panel
        cardsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardsPanel.setBackground(new Color(197, 220, 244));
        cardsPanel.add(createCard("Current Rooms", "View available rooms", "clipboard-list.png"));
        cardsPanel.add(createCard("Check Availability", "Search for a room", "search.png"));
        cardsPanel.add(createCard("Book Room", "Reserve an available room", "calendar-check.png"));

        dashboardPanel.add(cardsPanel, BorderLayout.CENTER);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(dashboardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private static JPanel createCard(String title, String subtitle, String iconPath) {
        JPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(200, 200));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(subtitleLabel);

        addCardInteractivity(card, title);

        return card;
    }

    private static void addCardInteractivity(JPanel card, String cardTitle) {
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(20, 108, 164));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                if (cardTitle.equals("Current Rooms")) {
                    // Code for when "Current Rooms" is clicked
                    new CurrentRoomFrame().setVisible(true);
                    System.out.println("Current Rooms card clicked!");
                    // Example: new CurrentRoomsFrame().setVisible(true);
                } else if (cardTitle.equals("Check Availability")) {
                    // Code for when "Check Availability" is clicked
                    new CheckRoomAvailability().setVisible(true);
                    // Example: new CheckAvailabilityFrame().setVisible(true);
                } else if (cardTitle.equals("Book Room")) {
                    
                    new RoomApplication().setVisible(true);
                }
            }
        });
    }

    static class RoundedPanel extends JPanel {

        public RoundedPanel() {
            super();
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new DashboardFrame();
    }

}

