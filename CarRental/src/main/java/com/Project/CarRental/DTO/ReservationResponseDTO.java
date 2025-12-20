package com.Project.CarRental.DTO;

import java.time.LocalDateTime;

public class ReservationResponseDTO {
    private String reservationNumber;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;
    private String pickupLocationCode;
    private String dropoffLocationCode;
    private String memberName;
    private String carBrand;
    private String carModel;
    private double totalAmount;

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }
    public LocalDateTime getPickupDateTime() { return pickupDateTime; }
    public void setPickupDateTime(LocalDateTime pickupDateTime) { this.pickupDateTime = pickupDateTime; }
    public LocalDateTime getDropoffDateTime() { return dropoffDateTime; }
    public void setDropoffDateTime(LocalDateTime dropoffDateTime) { this.dropoffDateTime = dropoffDateTime; }
    public String getPickupLocationCode() { return pickupLocationCode; }
    public void setPickupLocationCode(String pickupLocationCode) { this.pickupLocationCode = pickupLocationCode; }
    public String getDropoffLocationCode() { return dropoffLocationCode; }
    public void setDropoffLocationCode(String dropoffLocationCode) { this.dropoffLocationCode = dropoffLocationCode; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getCarBrand() { return carBrand; }
    public void setCarBrand(String carBrand) { this.carBrand = carBrand; }
    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
}