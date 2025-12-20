package com.Project.CarRental.Service;

import com.Project.CarRental.DTO.CarResponseDTO;
import com.Project.CarRental.Domain.Car;
import com.Project.CarRental.Domain.Reservation;
import com.Project.CarRental.Repository.CarRepository;
import com.Project.CarRental.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired private CarRepository carRepository;
    @Autowired private ReservationRepository reservationRepository;

    public List<CarResponseDTO> searchAvailableCars(String locationCode, LocalDateTime pickupDate, LocalDateTime dropoffDate) {
        List<Car> allCars = carRepository.findAll();

        return allCars.stream()
                .filter(car -> "AVAILABLE".equalsIgnoreCase(car.getStatus()))
                .filter(car -> locationCode == null || car.getLocation().getCode().equals(locationCode))
                .filter(car -> isCarAvailableForDates(car, pickupDate, dropoffDate))
                .map(this::convertCarToDTO)
                .collect(Collectors.toList());
    }

    public boolean returnCar(String reservationNumber) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        if (resOpt.isPresent()) {
            Reservation res = resOpt.get();
            res.setStatus("COMPLETED");
            res.setReturnDate(LocalDateTime.now());

            Car car = res.getCar();
            if (res.getDropoffLocation() != null) {
                car.setLocation(res.getDropoffLocation());
                carRepository.save(car);
            }
            reservationRepository.save(res);
            return true;
        }
        return false;
    }

    public boolean deleteCar(String barcode) {
        Optional<Car> carOpt = carRepository.findAll().stream()
                .filter(c -> c.getBarcode().equals(barcode))
                .findFirst();

        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            if (car.getReservations() != null && !car.getReservations().isEmpty()) {
                return false;
            }
            carRepository.delete(car);
            return true;
        }
        return false;
    }

    private boolean isCarAvailableForDates(Car car, LocalDateTime start, LocalDateTime end) {
        if (car.getReservations() == null) return true;
        for (Reservation r : car.getReservations()) {
            if ("ACTIVE".equals(r.getStatus())) {
                if (start.isBefore(r.getDropoffDateTime()) && end.isAfter(r.getPickupDateTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    private CarResponseDTO convertCarToDTO(Car car) {
        CarResponseDTO dto = new CarResponseDTO();
        dto.setBarcode(car.getBarcode());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setDailyPrice(car.getDailyPrice());
        dto.setCategory(car.getCategory());
        dto.setTransmissionType(car.getTransmissionType());
        dto.setLocationCode(car.getLocation().getCode());
        return dto;
    }
}