package com.example.finalproject;

public class Car {
    private String carModel;
    private String carType;
    private String ownerName;
    private String ownerContact;
    // Default constructor required for calls to DataSnapshot.getValue(Car.class)
    public Car() {
    }
    public Car(String carModel, String carType, String ownerName, String ownerContact) {
        this.carModel = carModel;
        this.carType = carType;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
    }
    // Getters and setters
    public String getCarModel() {
        return carModel;
    }
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerContact() {
        return ownerContact;
    }
    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }
}
