package com.Project.CarRental.Domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationNumber;
    private LocalDateTime creationDate;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropoffDateTime;
    private LocalDateTime returnDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_location_id")
    private Location pickupLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dropoff_location_id")
    private Location dropoffLocation;

    @ManyToMany
    @JoinTable(
            name = "reservation_extras",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private List<Extra> extras;

    public Reservation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public LocalDateTime getPickupDateTime() { return pickupDateTime; }
    public void setPickupDateTime(LocalDateTime pickupDateTime) { this.pickupDateTime = pickupDateTime; }

    public LocalDateTime getDropoffDateTime() { return dropoffDateTime; }
    public void setDropoffDateTime(LocalDateTime dropoffDateTime) { this.dropoffDateTime = dropoffDateTime; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public Location getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(Location pickupLocation) { this.pickupLocation = pickupLocation; }

    public Location getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(Location dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public List<Extra> getExtras() { return extras; }
    public void setExtras(List<Extra> extras) { this.extras = extras; }
}