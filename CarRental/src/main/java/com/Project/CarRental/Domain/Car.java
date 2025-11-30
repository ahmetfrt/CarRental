package com.Project.CarRental.Domain;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;
    private String licensePlateNumber;
    private int numberOfSeats;
    private String brand;
    private String model;
    private double mileage;
    private String transmissionType;
    private double dailyPrice;
    private String category;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY) // Compliance with Rule 13
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

    public Car() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getLicensePlateNumber() { return licensePlateNumber; }
    public void setLicensePlateNumber(String licensePlateNumber) { this.licensePlateNumber = licensePlateNumber; }

    public int getNumberOfSeats() { return numberOfSeats; }
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getMileage() { return mileage; }
    public void setMileage(double mileage) { this.mileage = mileage; }

    public String getTransmissionType() { return transmissionType; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }

    public double getDailyPrice() { return dailyPrice; }
    public void setDailyPrice(double dailyPrice) { this.dailyPrice = dailyPrice; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}