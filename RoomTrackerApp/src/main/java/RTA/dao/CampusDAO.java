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
public class CampusDAO {

    public void addCumpus(String name, String city, String address) {
        String sql = "INSERT INTO campus(name,city,address) VALUES (?,?,?)";
        try ( Connection conn = ConnectionManager.derbyConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, city);
            ps.setString(3, address);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCampuses() {
        String sql = "SELECT campus_id, name,city FROM campus";
        List<String> campuses = new ArrayList<>();
        try ( Connection conn = ConnectionManager.derbyConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                campuses.add(rs.getInt("ampus_id") + "-" + rs.getString("name") + "(" + rs.getString("city") + ")");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campuses;
    }
}
