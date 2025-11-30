package com.Project.CarRental.Repository;
import com.Project.CarRental.Domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}