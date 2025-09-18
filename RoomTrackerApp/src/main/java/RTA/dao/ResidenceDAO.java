/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.dao;

import RTA.connection.ConnectionManager;
import java.sql.*;
import java.util.*;

/**
 *
 * @author musot
 */
public class ResidenceDAO {

    public void addResidence(int campusID, String name, String address, String type) {
        String sql = "INSERT INTO reseidence(campus_id, name, address, type) VALUES(?,?,?,?)";
        try ( Connection conn = ConnectionManager.derbyConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, campusID);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getResidenceByCampus(int campusID) {
        List<String> residences = new ArrayList<>();
        String sql = " SELECT residence_id, name,address FROM residence WHERE campus_id=?";
        try ( Connection conn = ConnectionManager.derbyConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, campusID);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        residences.add(rs.getInt("residence_id") + " - " +
                                   rs.getString("name") + " | " +
                                   rs.getString("address"));
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residences;
    }

}
