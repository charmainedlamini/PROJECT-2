/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.Process;

import RTA.dao.RoomDAO;
import RTA.model.Room;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CurrentRoomFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private RoomDAO roomDAO;

    public CurrentRoomFrame() {
        setSize(800, 600);
        setTitle("Current Rooms Available");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setup table
        model = new DefaultTableModel(
                new String[]{"Room Number", "Residence", "Floor Number", "Available Beds", "Status"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View Details");
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // DAO
        roomDAO = new RoomDAO();
        loadRooms();

        // Actions
        viewButton.addActionListener(e -> viewRoomDetails());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadRooms() {
        model.setRowCount(0); // clear

        List<Room> rooms = roomDAO.getAvailableRooms("All"); // fetch all available rooms
        for (Room r : rooms) {
            model.addRow(new Object[]{
                    r.getRoomNumber(),
                    "Residence X", // TODO: join with residence table if needed
                    "Floor 1",     // TODO: if stored
                    r.getCapacity(), // treat capacity as "Available Beds"
                    r.getStatus()
            });
        }
    }

   private void viewRoomDetails() {
    List<Room> rooms = roomDAO.getAvailableRooms("All"); // get all available rooms

    if (rooms.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No available rooms found.");
        return;
    }

    // Build a message with all rooms
    StringBuilder sb = new StringBuilder("Available Rooms:\n\n");
    for (Room r : rooms) {
        sb.append("Room: ").append(r.getRoomNumber())
          .append(" | Capacity: ").append(r.getCapacity())
          .append(" | Status: ").append(r.getStatus())
          .append("\n");
    }

    // Show in a scrollable text area (if too many rooms)
    JTextArea textArea = new JTextArea(sb.toString());
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    JOptionPane.showMessageDialog(this, scrollPane, "All Available Rooms", JOptionPane.INFORMATION_MESSAGE);
}

}

