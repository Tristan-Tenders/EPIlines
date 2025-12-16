package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
    // Find airports by country
    List<Airport> findByCountry(String country);

    // Find airports by city
    List<Airport> findByCity(String city);

    // Find airports by name (partial match)
    List<Airport> findByNameContainingIgnoreCase(String name);

    // Find airports by country and city
    List<Airport> findByCountryAndCity(String country, String city);
}