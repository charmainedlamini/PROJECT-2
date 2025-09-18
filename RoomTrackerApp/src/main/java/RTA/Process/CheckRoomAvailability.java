/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.Process;

import RTA.dao.RoomDAO;
import RTA.model.Room;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author musot
 */

public class CheckRoomAvailability extends JFrame {

    private final JTable table;
    private final DefaultTableModel model;
    private final JComboBox<String> filterType;
    private final RoomDAO roomDAO;

    public CheckRoomAvailability() {
        setSize(800, 600);
        setTitle("Current Rooms Available");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        filterType = new JComboBox<>(new String[]{"All", "Male", "Female", "Mixed"});
        JButton filterBtn = new JButton("Filter");
        filterBtn.addActionListener(e -> loadAvailableRooms());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Gender Policy:"));
        topPanel.add(filterType);
        topPanel.add(filterBtn);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Room ID", "Room Number", "Capacity", "Gender Policy", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        roomDAO = new RoomDAO();
        loadAvailableRooms();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadAvailableRooms() {
        model.setRowCount(0); // clear table
        String selectedType = filterType.getSelectedItem().toString();

        List<Room> rooms = roomDAO.getAvailableRooms(selectedType);

        for (Room r : rooms) {
            model.addRow(new Object[]{
                    r.getRoomId(),
                    r.getRoomNumber(),
                    r.getCapacity(),
                    r.getGenderPolicy(),
                    r.getStatus()
            });
        }
    }

    public static void main(String[] args) {
        new CheckRoomAvailability();
    }
}
