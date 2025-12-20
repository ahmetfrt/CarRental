package com.Project.CarRental.Controller;

import com.Project.CarRental.DTO.*;
import com.Project.CarRental.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationRequestDTO request) {
        try {
            ReservationResponseDTO response = reservationService.makeReservation(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("available")) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rented")
    public ResponseEntity<List<CarResponseDTO>> getRentedCars() {
        List<CarResponseDTO> cars = reservationService.getRentedCars();
        if (cars.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/{resNumber}/extras/{extraId}")
    public ResponseEntity<Void> addExtra(@PathVariable String resNumber, @PathVariable Long extraId) {
        boolean success = reservationService.addExtra(resNumber, extraId);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{resNumber}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable String resNumber) {
        boolean success = reservationService.cancelReservation(resNumber);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{resNumber}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String resNumber) {
        boolean success = reservationService.deleteReservation(resNumber);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}