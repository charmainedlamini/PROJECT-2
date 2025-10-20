
package za.ac.cput.roomtrackerapp; //package name

/**
 *
 * @author musot
 * @author Charmaine Dlamini
 */
import RTA.Process.CheckRoomAvailability; // Import frame to check room availability
import RTA.Process.CurrentRoomFrame;      // Import frame to view current rooms
import RTA.Process.RoomApplication;       // Import frame to apply/book rooms
import RTA.model.LoginSession;            // Import LoginSession class to retrieve logged-in user's email

import javax.swing.*;                      // Import Swing components for GUI
import java.awt.*;                         // Import AWT for layouts and colors
import java.awt.event.*;                   // Import AWT events for button and mouse actions
import javax.swing.border.EmptyBorder;     // Import for padding/margin around components

public class DashboardFrame extends JFrame {

   // Declare panels and components used in the dashboard
  //JPanel- containers for organizing content
  //JLabel- for text labels
  //JButton for buttons
    private JPanel headerPanel, logoAndTitlePanel, titlePanel, emailAndButtonPanel, dashboardPanel, cardsPanel;
    private JLabel logoLabel, line1, line2, emailLabel, dashboardTitle;
    private JButton btnLogout;
    private JFrame frame;

    // Constructor that initializes and builds the GUI when a DashboardFrame object is created
    public DashboardFrame() {
        super("Dashboard"); //Sets the window title to "Dashboard"
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Closes the program completely when the window is closed
        setSize(800, 500); //Sets the initial size of the window to 800x500 pixels
        setLayout(new BorderLayout()); //Uses BorderLayout for the main frame (top, bottom, center, left, right sections)
        getContentPane().setBackground(new Color(197, 220, 244)); //Sets the background color of the main window
        setLocationRelativeTo(null); //Centers the window on the screen

        // Initialize header panel with a border layout
        // Sets background color light blue
        // set border padding of 20px on all sides
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(197, 220, 244));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize logo and title panel aligned it left, with horizontal spacing of 10px
        // Sets background color light blue
        logoAndTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoAndTitlePanel.setBackground(new Color(197, 220, 244));

        // Initialize logo, Loads the CPUT logo, scales it to 60x60, wraps it in a label, and adds it to the logo/title panel
        ImageIcon logoIcon = new ImageIcon("CPUT-LOGO2.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoAndTitlePanel.add(logoLabel);

        // Initialize and creates a panel with 2 rows, 1 column to display the two lines of the university name
        titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(197, 220, 244));

        //First line of the title: text "Cape Peninsula", left-aligned, bold, blue color
        line1 = new JLabel("Cape Peninsula", JLabel.LEFT);
        line1.setFont(new Font("Roboto", Font.BOLD, 22));
        line1.setForeground(new Color(34, 58, 136));

        //Second line of the title: "University of Technology" with same style
        line2 = new JLabel("University of Technology", JLabel.LEFT);
        line2.setFont(new Font("Roboto", Font.BOLD, 22));
        line2.setForeground(new Color(34, 58, 136));
        
        //Adds both title lines to the titlePanel, then adds it next to the logo, and places the whole thing on the left side of the header
        titlePanel.add(line1);
        titlePanel.add(line2);
        logoAndTitlePanel.add(titlePanel);
        headerPanel.add(logoAndTitlePanel, BorderLayout.WEST);

        // Panel for showing the email and logout button, aligned right
        emailAndButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        emailAndButtonPanel.setBackground(new Color(197, 220, 244));

        //Label to display logged-in email
        emailLabel = new JLabel("example@example.com");
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        emailAndButtonPanel.add(emailLabel);

        //Creates logout button with font, colors, padding, and removes default focus border
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
                // I fixed the logout button when you logout it will go back to the welcome frame
                // Show a confirmation dialog
                int confirmed = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                // If the user confirmed, show a message dialog and then dispose of the frame
                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose(); // close dashboard
                    new WelcomeFrame().setVisible(true); // return to welcome frame
                }
            }
        });

        //Adds logout button to email panel, then places it on right side of header
        emailAndButtonPanel.add(btnLogout);
        headerPanel.add(emailAndButtonPanel, BorderLayout.EAST);

        // Initialize dashboard panel
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(new Color(197, 220, 244));
        dashboardPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        //Adds a title label at the top of the dashboard panel
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

        //Shows the window and loads the actual logged-in user's email
        setVisible(true);
        loadStudentEmail();
    }
    //also added this method to show email in the dashboard after user logged in
    // Load the student's email from the login session
     private void loadStudentEmail() {
        String email = LoginSession.getEmail(); // get email from session
            if (email != null && !email.isEmpty()) {
                emailLabel.setText(email);
            } else {
                emailLabel.setText("example@example.com"); // fallback
    }
}

    //Creates a card panel with: Rounded panel Icon, title, subtitle, Vertical spacing, Interactivity (hover and click)
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

    //Declares a private static method called addCardInteractivity.
    //Parameters:JPanel card - the card panel to which we want to add interactivity (hover and click effects)
    //String cardTitle - the title of the card, used to determine what action to perform when clicked
    private static void addCardInteractivity(JPanel card, String cardTitle) {
        card.addMouseListener(new java.awt.event.MouseAdapter() { //Adds a mouse listener to the card panel
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(20, 108, 164)); //Changes the card’s background color to a darker blue when hovered
            }

            @Override //Overrides the mouseEntered method, This method is called when the mouse cursor moves over the card
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE); //Resets the card’s background color to white when the mouse is no longer over it
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

    //Declares a static inner class RoundedPanel that extends JPanel
   //This custom panel will allow us to draw panels with rounded corners
    //static means it can be used without an instance of the outer DashboardFrame class
    static class RoundedPanel extends JPanel {

        //Constructor for RoundedPanel
        //super(); calls the JPanel constructor to initialize the panel
    //setOpaque(false); makes the panel transparent so the custom rounded painting shows correctly
        public RoundedPanel() {
            super();
            setOpaque(false);
        }

       // Overrides the paintComponent method of JPanel
      //This method is called automatically whenever the panel needs to be drawn
     //Graphics g  provides drawing methods
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create(); //Creates a Graphics2D object from the Graphics object
            //Graphics2D is more advanced and allows smooth shapes, anti-aliasing, and better control
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing for smoother edges on shapes
            g2.setColor(getBackground()); // Set the drawing color to the panel's background color
            // Draw a filled rectangle with rounded corners
            // Parameters: x, y, width, height, arcWidth, arcHeight
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose(); // Release the Graphics2D resources to avoid memory leaks
            super.paintComponent(g); // Call the original JPanel paintComponent to ensure default painting occurs
        }
    }

    //// This is the entry point of the Java application.
    public static void main(String[] args) {
        new DashboardFrame();// Creates a new instance of the DashboardFrame class, When the constructor of DashboardFrame is called, the GUI window is created and displayed
    }

}



