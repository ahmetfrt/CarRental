package com.example.demo.domain;

import java.util.List;

public class Location {

    private String code;
    private String name;

    // A location can have many cars
    private List<Car> cars;

    // A location can be the pickup point for many reservations
    private List<Reservation> pickupReservations;

    // A location can be the dropoff point for many reservations
    private List<Reservation> dropoffReservations;

    // Constructor
    public Location() {
    }

    // --- Getters and Setters ---

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Reservation> getPickupReservations() {
        return pickupReservations;
    }

    public void setPickupReservations(List<Reservation> pickupReservations) {
        this.pickupReservations = pickupReservations;
    }

    public List<Reservation> getDropoffReservations() {
        return dropoffReservations;
    }

    public void setDropoffReservations(List<Reservation> dropoffReservations) {
        this.dropoffReservations = dropoffReservations;
    }
}