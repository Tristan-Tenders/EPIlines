package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Airport;
import com.timeoutairline.epiline.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    /**
     * Get all airports
     */

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    /**
     * Get airport by ID
     */

    public Optional<Airport> getAirportById(Long airportId) {
        return airportRepository.findById(airportId);
    }

    /**
     * Get airports by country
     */

    public List<Airport> getAirportsByCountry(String country) {
        return airportRepository.findByCountry(country);
    }

    /**
     * Get airports by city
     */

    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

    /**
     * Search airports by name
     */

    public List<Airport> searchAirportsByName(String name) {
        return airportRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Get airports by country and city
     */

    public List<Airport> getAirportsByCountryAndCity(String country, String city) {
        return airportRepository.findByCountryAndCity(country, city);
    }

    /**
     * Save a new airport
     */

    public Airport saveAirport(Airport airport) {
        if (airport.getAirportId() != null) {
            return airportRepository.save(airport);
        }
        return null;
    }

    /**
     * Update existing airport
     * Returns null if airport doesn't exist
     */

    public Airport updateAirport(Long airportId, Airport updatedAirport) {
        if (airportRepository.existsById(airportId)) {
            updatedAirport.setAirportId(airportId);

            return airportRepository.save(updatedAirport);
        }
        return null;
    }

    /**
     * Delete airport by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteAirport(Long airportId) {
        if (airportRepository.existsById(airportId)) {
            airportRepository.deleteById(airportId);
            return true;
        }
        return false;
    }

}