package com.Project.CarRental.Repository;
import com.Project.CarRental.Domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}