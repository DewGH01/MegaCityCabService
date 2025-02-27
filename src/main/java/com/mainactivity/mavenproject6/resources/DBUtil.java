/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mainactivity.mavenproject6.resources;

/**
 *
 * @author Dewmi
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {
     private Connection conn;

    // Constructor to initialize the database connection
    public DBUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/megacitycab", "root", "");
            System.out.println("✅ Connected to MySQL Database!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ MySQL Connection Failed!");
        }
    }

    // -------------- User API -----------------

    // Check if user exists by email
    public boolean checkUserExists(String email) {
        boolean exists = false;
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Sign-Up: Collect registration number, name, address, NIC, email, password.
    public void signUpUser(User user) {
        try {
            String query = "INSERT INTO user (registration_number, name, address, nic, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getRegistrationNumber());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getAddress());
            pstmt.setString(4, user.getNic());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPassword());
            pstmt.setString(7, user.getRole());
            pstmt.executeUpdate();
            System.out.println("✅ User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Login: Authenticate using email and password.
    public User loginUser(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("registration_number"), rs.getString("name"),
                        rs.getString("address"), rs.getString("nic"), rs.getString("email"),
                        rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Get User: Get details of the logged-in user.
    public User getUser(int userId) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("registration_number"), rs.getString("name"),
                        rs.getString("address"), rs.getString("nic"), rs.getString("email"),
                        rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Get All Users: For admin to view all users.
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("registration_number"), rs.getString("name"),
                        rs.getString("address"), rs.getString("nic"), rs.getString("email"),
                        rs.getString("password"), rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update User: Admin can update user details.
    public boolean updateUser(User user) {
        boolean updated = false;
        try {
            String query = "UPDATE user SET name = ?, address = ?, nic = ?, email = ?, password = ?, role = ? WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getNic());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getRole());
            pstmt.setInt(7, user.getUserId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    // Delete User: Admin can delete users.
    public boolean deleteUser(String email) {
        boolean deleted = false;
        try {
            String query = "DELETE FROM user WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    // -------------- Car API ----------------

// Get All Cars: Retrieve all cars (Admin view)
public List<Car> getAllCars() {
    List<Car> cars = new ArrayList<>();
    try {
        String query = "SELECT * FROM car";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            cars.add(new Car(
                rs.getInt("car_id"),
                rs.getString("model"),
                rs.getString("make"),
                rs.getInt("year"),
                rs.getDouble("price_per_km"),
                rs.getBoolean("is_available")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cars;
}

// Get Car by ID: Retrieve a specific car's details using its ID.
public Car getCarById(int carId) {
    Car car = null;
    try {
        String query = "SELECT * FROM car WHERE car_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, carId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            car = new Car(
                rs.getInt("car_id"),
                rs.getString("model"),
                rs.getString("make"),
                rs.getInt("year"),
                rs.getDouble("price_per_km"),
                rs.getBoolean("is_available")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return car;
}

// Create Car: Add a new car to the system.
public void createCar(Car car) {
    try {
        String query = "INSERT INTO car (model, make, year, price_per_km, is_available) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, car.getModel());
        pstmt.setString(2, car.getMake());
        pstmt.setInt(3, car.getYear());
        pstmt.setDouble(4, car.getPricePerKm());
        pstmt.setBoolean(5, car.isAvailable());
        pstmt.executeUpdate();
        System.out.println("✅ Car created successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Update Car: Admin can modify car details.
public void updateCar(Car car) {
    try {
        String query = "UPDATE car SET model = ?, make = ?, year = ?, price_per_km = ?, is_available = ? WHERE car_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, car.getModel());
        pstmt.setString(2, car.getMake());
        pstmt.setInt(3, car.getYear());
        pstmt.setDouble(4, car.getPricePerKm());
        pstmt.setBoolean(5, car.isAvailable());
        pstmt.setInt(6, car.getCarId());
        pstmt.executeUpdate();
        System.out.println("✅ Car updated successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Delete Car: Admin can delete a car from the system.
public void deleteCar(int carId) {
    try {
        String query = "DELETE FROM car WHERE car_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, carId);
        pstmt.executeUpdate();
        System.out.println("✅ Car deleted successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Check Car Availability: Ensure the car is available for the selected date.
public boolean checkCarAvailability(int carId) {
    try {
        String query = "SELECT is_available FROM car WHERE car_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, carId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getBoolean("is_available");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Car is not available
}


// -------------- Driver API -----------------

    // Create Driver
    public void createDriver(Driver driver) {
        try {
            String query = "INSERT INTO driver (driver_name, driver_phone, availability, driver_license) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, driver.getDriverName());
            pstmt.setString(2, driver.getDriverPhone());
            pstmt.setBoolean(3, driver.isAvailable());
            pstmt.setString(4, driver.getDriverLicense());
            pstmt.executeUpdate();
            System.out.println("✅ Driver created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get All Drivers
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try {
            String query = "SELECT * FROM driver";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("driver_id"), rs.getString("driver_name"), rs.getString("driver_phone"),
                        rs.getBoolean("availability"), rs.getString("driver_license")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Get Driver by ID
    public Driver getDriverById(int driverId) {
        Driver driver = null;
        try {
            String query = "SELECT * FROM driver WHERE driver_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, driverId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                driver = new Driver(rs.getInt("driver_id"), rs.getString("driver_name"), rs.getString("driver_phone"),
                        rs.getBoolean("availability"), rs.getString("driver_license"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    // Update Driver
    public void updateDriver(Driver driver) {
        try {
            String query = "UPDATE driver SET driver_name = ?, driver_phone = ?, availability = ?, driver_license = ? WHERE driver_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, driver.getDriverName());
            pstmt.setString(2, driver.getDriverPhone());
            pstmt.setBoolean(3, driver.isAvailable());
            pstmt.setString(4, driver.getDriverLicense());
            pstmt.setInt(5, driver.getDriverId());
            pstmt.executeUpdate();
            System.out.println("✅ Driver updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Driver
    public void deleteDriver(int driverId) {
        try {
            String query = "DELETE FROM driver WHERE driver_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, driverId);
            pstmt.executeUpdate();
            System.out.println("✅ Driver deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check Driver Availability
    public boolean checkDriverAvailability(int driverId) {
        boolean isAvailable = false;
        try {
            String query = "SELECT availability FROM driver WHERE driver_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, driverId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                isAvailable = rs.getBoolean("availability");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAvailable;
    }
    
    // Close the connection
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✅ Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
