package com.Project.CarRental.Controller;

import com.Project.CarRental.DTO.CarResponseDTO;
import com.Project.CarRental.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired private CarService carService;

    @GetMapping("/available")
    public ResponseEntity<List<CarResponseDTO>> searchAvailableCars(
            @RequestParam(required = false) String locationCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pickupDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dropoffDate) {

        List<CarResponseDTO> cars = carService.searchAvailableCars(locationCode, pickupDate, dropoffDate);
        if (cars.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/reservations/{resNumber}/return")
    public ResponseEntity<Void> returnCar(@PathVariable String resNumber) {
        boolean success = carService.returnCar(resNumber);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteCar(@PathVariable String barcode) {
        boolean success = carService.deleteCar(barcode);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}