package com.Project.CarRental.DTO;

public class CarResponseDTO {
    private String barcode;
    private String brand;
    private String model;
    private double dailyPrice;
    private String category;
    private String transmissionType;
    private String locationCode;

    public CarResponseDTO() {}
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getDailyPrice() { return dailyPrice; }
    public void setDailyPrice(double dailyPrice) { this.dailyPrice = dailyPrice; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTransmissionType() { return transmissionType; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }
    public String getLocationCode() { return locationCode; }
    public void setLocationCode(String locationCode) { this.locationCode = locationCode; }
}
