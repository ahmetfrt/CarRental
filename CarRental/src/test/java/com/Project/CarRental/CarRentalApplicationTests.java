package com.Project.CarRental;

import com.Project.CarRental.DTO.CarResponseDTO;
import com.Project.CarRental.DTO.ReservationRequestDTO;
import com.Project.CarRental.DTO.ReservationResponseDTO;
import com.Project.CarRental.Service.CarService;
import com.Project.CarRental.Service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarRentalApplicationTests {

    @Autowired private CarService carService;
    @Autowired private ReservationService reservationService;

    @Test
    void testSearchAvailableCars() {
        LocalDateTime searchStart = LocalDateTime.now().plusDays(10);
        LocalDateTime searchEnd = LocalDateTime.now().plusDays(12);

        List<CarResponseDTO> availableCars = carService.searchAvailableCars(
                "IST",
                searchStart,
                searchEnd
        );

        assertFalse(availableCars.isEmpty(), "Should find at least one car at IST (Toyota Corolla)");

        assertEquals("Toyota", availableCars.get(0).getBrand());
        assertEquals("Corolla", availableCars.get(0).getModel());
    }

    @Test
    void testMakeAndCancelReservation() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setCarBarcode("CAR001");
        request.setMemberId(1L);

        request.setPickupDateTime(LocalDateTime.now().plusDays(20));
        request.setDropoffDateTime(LocalDateTime.now().plusDays(25));
        request.setPickupLocationCode("IST");
        request.setDropoffLocationCode("IST");

        ReservationResponseDTO response = reservationService.makeReservation(request);

        assertNotNull(response);
        assertNotNull(response.getReservationNumber());
        assertEquals("Toyota", response.getCarBrand());

        boolean isCancelled = reservationService.cancelReservation(response.getReservationNumber());
        assertTrue(isCancelled, "Cancel should return true");
    }

    @Test
    void testDeleteCarFailureRule() {
        boolean result = carService.deleteCar("CAR001");

        assertFalse(result, "Should not delete car that has active reservations");
    }
}