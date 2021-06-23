package com.aravind.models;

/**
 * Model to represent Car details
 */
public class Car {

    private String registrationNumber;
    private String colour;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public Car(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }
}
