/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mainactivity.mavenproject6.resources;

/**
 *
 * @author Dewmi
 */
public class User {
    private int userId;
    private String registrationNumber;
    private String name;
    private String address;
    private String nic;
    private String email;
    private String password;
    private String role; // "admin" or "customer"

    // Constructor
    public User(int userId, String registrationNumber, String name, String address, String nic, String email, String password, String role) {
        this.userId = userId;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String registrationNumber, String name, String address, String nic, String email, String password, String role) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
