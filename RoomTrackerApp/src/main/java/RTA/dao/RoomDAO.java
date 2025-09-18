/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.dao;

import RTA.connection.ConnectionManager;
import RTA.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author musot
 */
public class RoomDAO {
    // Add a new room
    public void addRoom(int buildingId, String roomNumber, int capacity, String genderPolicy, String status) {
        String sql = "INSERT INTO room (building_id, room_number, capacity, gender_policy, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.derbyConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buildingId);
            ps.setString(2, roomNumber);
            ps.setInt(3, capacity);
            ps.setString(4, genderPolicy);
            ps.setString(5, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all available rooms
    public List<Room> getAvailableRooms(String type) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT room_id, room_number, capacity, gender_policy, status FROM room WHERE status='Available'";

        if (!type.equals("All")) {
            sql += " AND gender_policy = '" + type + "'";
        }

        try (Connection conn = ConnectionManager.derbyConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getString("gender_policy"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}

