package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * FlightRepository - Data access layer for Flight entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Find flights by departure city
    List<Flight> findByDepCity(String depCity);

    // Find flights by arrival city
    List<Flight> findByArrCity(String arrCity);

    // Find flights by departure and arrival cities
    List<Flight> findByDepCityAndArrCity(String depCity, String arrCity);

    // Find flights by departure airport
    List<Flight> findByDepAirportId(Long depAirportId);

    // Find flights by arrival airport
    List<Flight> findByArrAirportId(Long arrAirportId);

    // Find flights by plane
    List<Flight> findByPlaneId(Long planeId);

    // Find flights by status
    List<Flight> findByStatus(String status);

    // Find flights departing after a certain time
    List<Flight> findByDepTimeAfter(LocalDateTime depTime);

    // Find flights departing between two times
    List<Flight> findByDepTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Find available flights (with available seats > 0)
    List<Flight> findByAvailableSeatsGreaterThan(Integer seats);

    // Find flights by departure city and status
    List<Flight> findByDepCityAndStatus(String depCity, String status);

    // Find flights by arrival city and status
    List<Flight> findByArrCityAndStatus(String arrCity, String status);
}