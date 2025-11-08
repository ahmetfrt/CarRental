package com.example.demo.domain;

import java.util.List;

public class Extra {

    private String name;
    private double price;

    // An extra can be part of many reservations
    private List<Reservation> reservations;

    // Constructor
    public Extra() {
    }

    // --- Getters and Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}