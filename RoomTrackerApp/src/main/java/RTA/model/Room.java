/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.model;

/**
 *
 * @author musot
 */
public class Room {

    private int roomId;
    private String roomNumber;
    private int capacity;
    private String genderPolicy;
    private String status;

    public Room(int roomId, String roomNumber, int capacity, String genderPolicy, String status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.genderPolicy = genderPolicy;
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getGenderPolicy() {
        return genderPolicy;
    }

    public String getStatus() {
        return status;
    }
}
