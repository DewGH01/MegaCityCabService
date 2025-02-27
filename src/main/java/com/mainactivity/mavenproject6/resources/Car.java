/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mainactivity.mavenproject6.resources;

/**
 *
 * @author Dewmi
 */
public class Car {
    private int carId;
    private String model;
    private String make;
    private int year;
    private double pricePerKm;
    private boolean isAvailable;

    // Constructor
    public Car(int carId, String model, String make, int year, double pricePerKm, boolean isAvailable) {
        this.carId = carId;
        this.model = model;
        this.make = make;
        this.year = year;
        this.pricePerKm = pricePerKm;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
