/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.dao;

import RTA.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author musot
 */
public class StudentDAO {

    public void addStudent(String studentNumber, String firstName, String lastName, String email, String phone) {
        String sql = "INSERT INTO student(student_number, first_name, last_name, email,phone) VALUES(?,?,?,?,?)";
        try ( Connection conn = ConnectionManager.derbyConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentNumber);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all
    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        String sql = "SELECT student_id, first_name, last_name FROM student";
        try ( Connection conn = ConnectionManager.derbyConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(rs.getInt("student_id") + " - "
                        + rs.getString("first_name") + " "
                        + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
