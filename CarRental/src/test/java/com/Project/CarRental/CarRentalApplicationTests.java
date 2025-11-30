package com.Project.CarRental;

import com.Project.CarRental.Domain.Car;
import com.Project.CarRental.Service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRentalApplicationTests {

    @Autowired
    private CarService carService;

    @Test
    void testCreateAndGetCar() {
        Car car = new Car();
        car.setBrand("Tesla");
        car.setModel("Model 3");

        Car savedCar = carService.createCar(car);
        assertNotNull(savedCar.getId());

        Car foundCar = carService.getCarById(savedCar.getId()).orElse(null);
        assertNotNull(foundCar);
        assertEquals("Tesla", foundCar.getBrand());
    }
}