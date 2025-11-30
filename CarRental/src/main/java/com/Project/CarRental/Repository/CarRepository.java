package com.Project.CarRental.Repository;
import com.Project.CarRental.Domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}