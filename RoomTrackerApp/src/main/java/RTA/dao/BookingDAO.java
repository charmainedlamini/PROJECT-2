/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.dao;

import RTA.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author musot
 */
public class BookingDAO {

    public void createBooking(int studentId, int roomId, Date startDate, Date endDate) {
        String sql = "INSERT INTO booking (student_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        try ( Connection conn = ConnectionManager.derbyConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, roomId);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get bookings by student
    public List<String> getBookingsByStudent(int studentId) {
        List<String> bookings = new ArrayList<>();
        String sql = "SELECT booking_id, status, start_date, end_date FROM booking WHERE student_id=?";
        try ( Connection conn = ConnectionManager.derbyConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add("Booking " + rs.getInt("booking_id")
                            + " - " + rs.getString("status")
                            + " (" + rs.getDate("start_date")
                            + " to " + rs.getDate("end_date") + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

}
