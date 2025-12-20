package com.Project.CarRental;

import com.Project.CarRental.Domain.*;
import com.Project.CarRental.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private LocationRepository locationRepository;
    @Autowired private CarRepository carRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ExtraRepository extraRepository;
    @Autowired private ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
        Location loc1 = new Location();
        loc1.setCode("IST");
        loc1.setName("Istanbul Airport");
        locationRepository.save(loc1);

        Location loc2 = new Location();
        loc2.setCode("SAW");
        loc2.setName("Sabiha Gokcen");
        locationRepository.save(loc2);

        Car car1 = new Car();
        car1.setBarcode("CAR001");
        car1.setBrand("Toyota");
        car1.setModel("Corolla");
        car1.setDailyPrice(100.0);
        car1.setNumberOfSeats(5);
        car1.setTransmissionType("Automatic");
        car1.setStatus("AVAILABLE"); // Important for logic
        car1.setLocation(loc1);
        carRepository.save(car1);

        Member member1 = new Member();
        member1.setName("John Doe");
        member1.setEmail("john@example.com");
        member1.setPhone("5551234567");
        member1.setAddress("Istanbul");
        member1.setDrivingLicenseNumber("DL123456");
        memberRepository.save(member1);

        Extra gps = new Extra();
        gps.setName("GPS");
        gps.setPrice(50.0);
        extraRepository.save(gps);

        Extra seat = new Extra();
        seat.setName("Baby Seat");
        seat.setPrice(30.0);
        extraRepository.save(seat);

        Reservation res1 = new Reservation();
        res1.setReservationNumber("RES12345"); // 8 digits/chars
        res1.setCreationDate(LocalDateTime.now());
        res1.setPickupDateTime(LocalDateTime.now().plusDays(1));
        res1.setDropoffDateTime(LocalDateTime.now().plusDays(3));
        res1.setStatus("ACTIVE");
        res1.setMember(member1);
        res1.setCar(car1);
        res1.setPickupLocation(loc1);
        res1.setDropoffLocation(loc2);

        List<Extra> resExtras = new ArrayList<>();
        resExtras.add(gps);
        res1.setExtras(resExtras);

        reservationRepository.save(res1);

        System.out.println("--- All Tables Populated Successfully ---");
    }
}