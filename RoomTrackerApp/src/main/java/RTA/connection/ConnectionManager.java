/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.connection;

/**
 *
 * @author musot
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection derbyConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/cput_room_tracker";
        String username = "Musotuks";
        String password = "12345";
        Connection myCon = DriverManager.getConnection(url, username, password);
        return myCon;
    }
 
}
