package com.Project.CarRental;

import com.Project.CarRental.Domain.*;
import com.Project.CarRental.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        Location loc1 = new Location();
        loc1.setCode("IST");
        loc1.setName("Istanbul Airport");
        locationRepository.save(loc1);

        Car car1 = new Car();
        car1.setBarcode("12345");
        car1.setBrand("Toyota");
        car1.setModel("Corolla");
        car1.setDailyPrice(100.0);
        car1.setLocation(loc1);
        carRepository.save(car1);

        System.out.println("--- Initial Data Loaded ---");
    }
}