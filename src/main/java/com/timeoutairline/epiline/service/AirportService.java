package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Airport;
import com.timeoutairline.epiline.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * AirportService - Business logic layer for Airport operations
 * Matches the style of UserService in your project
 */
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
     * Get airport by IATA code
     */
    public Optional<Airport> getAirportByCode(String code) {
        return airportRepository.findByCode(code.toUpperCase());
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
        // Convert code to uppercase
        if (airport.getCode() != null) {
            airport.setCode(airport.getCode().toUpperCase());
        }
        return airportRepository.save(airport);
    }

    /**
     * Update existing airport
     * Returns null if airport doesn't exist
     */
    public Airport updateAirport(Long airportId, Airport updatedAirport) {
        if (airportRepository.existsById(airportId)) {
            updatedAirport.setAirportId(airportId);
            // Convert code to uppercase
            if (updatedAirport.getCode() != null) {
                updatedAirport.setCode(updatedAirport.getCode().toUpperCase());
            }
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

    /**
     * Check if airport code exists
     */
    public boolean codeExists(String code) {
        return airportRepository.existsByCode(code.toUpperCase());
    }
}