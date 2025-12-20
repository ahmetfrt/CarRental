package com.Project.CarRental.Service;

import com.Project.CarRental.DTO.*;
import com.Project.CarRental.Domain.*;
import com.Project.CarRental.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private CarRepository carRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private LocationRepository locationRepository;
    @Autowired private ExtraRepository extraRepository;

    public ReservationResponseDTO makeReservation(ReservationRequestDTO request) {
        Car car = carRepository.findAll().stream()
                .filter(c -> c.getBarcode().equals(request.getCarBarcode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!"AVAILABLE".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("Car not available"); // Controller will handle this as 406
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Location pickupLoc = locationRepository.findAll().stream()
                .filter(l -> l.getCode().equals(request.getPickupLocationCode()))
                .findFirst().orElse(car.getLocation());
        Location dropoffLoc = locationRepository.findAll().stream()
                .filter(l -> l.getCode().equals(request.getDropoffLocationCode()))
                .findFirst().orElse(pickupLoc);

        Reservation res = new Reservation();
        res.setCar(car);
        res.setMember(member);
        res.setPickupDateTime(request.getPickupDateTime());
        res.setDropoffDateTime(request.getDropoffDateTime());
        res.setPickupLocation(pickupLoc);
        res.setDropoffLocation(dropoffLoc);
        res.setCreationDate(LocalDateTime.now());
        res.setStatus("ACTIVE");
        res.setReservationNumber(String.valueOf(10000000 + new Random().nextInt(90000000)));

        if (request.getExtraIds() != null) {
            List<Extra> extras = extraRepository.findAllById(request.getExtraIds());
            res.setExtras(extras);
        }

        Reservation savedRes = reservationRepository.save(res);

        return convertToResponseDTO(savedRes);
    }

    public List<CarResponseDTO> getRentedCars() {
        return reservationRepository.findAll().stream()
                .filter(r -> "ACTIVE".equals(r.getStatus()))
                .map(r -> convertCarToDTO(r.getCar()))
                .collect(Collectors.toList());
    }


    public boolean addExtra(String reservationNumber, Long extraId) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        Optional<Extra> extraOpt = extraRepository.findById(extraId);

        if (resOpt.isPresent() && extraOpt.isPresent()) {
            Reservation res = resOpt.get();
            if (!res.getExtras().contains(extraOpt.get())) {
                res.getExtras().add(extraOpt.get());
                reservationRepository.save(res);
                return true;
            }
        }
        return false;
    }

    public boolean cancelReservation(String reservationNumber) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        if (resOpt.isPresent()) {
            Reservation res = resOpt.get();
            res.setStatus("CANCELLED");
            reservationRepository.save(res);
            return true;
        }
        return false;
    }

    public boolean deleteReservation(String reservationNumber) {
        Optional<Reservation> resOpt = reservationRepository.findByReservationNumber(reservationNumber);
        if (resOpt.isPresent()) {
            Reservation res = resOpt.get();
            res.setCar(null); // Disassociate
            res.setMember(null);
            res.setExtras(null);
            reservationRepository.delete(res);
            return true;
        }
        return false;
    }

    private ReservationResponseDTO convertToResponseDTO(Reservation res) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setReservationNumber(res.getReservationNumber());
        dto.setPickupDateTime(res.getPickupDateTime());
        dto.setDropoffDateTime(res.getDropoffDateTime());
        dto.setPickupLocationCode(res.getPickupLocation().getCode());
        dto.setDropoffLocationCode(res.getDropoffLocation().getCode());
        dto.setMemberName(res.getMember().getName());
        dto.setCarBrand(res.getCar().getBrand());
        dto.setCarModel(res.getCar().getModel());

        long days = ChronoUnit.DAYS.between(res.getPickupDateTime(), res.getDropoffDateTime());
        if (days < 1) days = 1; // Minimum 1 day

        double carCost = days * res.getCar().getDailyPrice();
        double extrasCost = 0;
        if (res.getExtras() != null) {
            extrasCost = res.getExtras().stream().mapToDouble(Extra::getPrice).sum();
        }

        dto.setTotalAmount(carCost + extrasCost);
        return dto;
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