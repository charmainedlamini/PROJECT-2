/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.Domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author musot
 */
public class TrackandSecureSys {

    private static final String USERS_FILE = "users.txt";
    private static final String APPLICATIONS_FILE = "applications.txt";

    public static void registerUser(String username, String password, String studentID, String email) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password + "," + studentID + "," + email);
            writer.newLine();
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public static boolean loginUser(String username, String password) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
        return false;
    }

    public static void submitApplication(String studentID, String roomType, String date) {
        String applicationID = UUID.randomUUID().toString(); // Generate unique ID
        String status = "Pending";
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(APPLICATIONS_FILE, true))) {
            writer.write(applicationID + "," + studentID + "," + roomType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Application submitted successfully!");
        } catch (IOException e) {
            System.out.println("Error submitting application: " + e.getMessage());
        }
    }

    public static void viewApplications(String studentID) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(APPLICATIONS_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[1].equals(studentID)) {
                    System.out.println("ApplicationID: " + parts[0]
                            + " | RoomType: " + parts[2]
                            + " | Date: " + parts[3]
                            + " | Status: " + parts[4]);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No applications found for studentID: " + studentID);
            }
        } catch (IOException e) {
            System.out.println("Error viewing applications: " + e.getMessage());
        }
    }

    public static void confirmApplication(String applicationID) {
        File inputFile = new File(APPLICATIONS_FILE);
        File tempFile = new File("temp.txt");

        try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile));  BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equals(applicationID)) {
                    parts[4] = "Confirmed"; // Update status
                    line = String.join(",", parts);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error confirming application: " + e.getMessage());
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            System.out.println("Application confirmed successfully!");
        } else {
            System.out.println("Error updating application.");
        }

    }
}
