package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * AirportRepository - Data access layer for Airport entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    // Find airport by IATA code (e.g., "CDG", "JFK")
    Optional<Airport> findByCode(String code);

    // Find airports by country
    List<Airport> findByCountry(String country);

    // Find airports by city
    List<Airport> findByCity(String city);

    // Find airports by name (partial match)
    List<Airport> findByNameContainingIgnoreCase(String name);

    // Find airports by country and city
    List<Airport> findByCountryAndCity(String country, String city);

    // Check if airport code exists
    boolean existsByCode(String code);
}