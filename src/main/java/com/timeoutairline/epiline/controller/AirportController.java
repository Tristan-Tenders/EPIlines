package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Airport;
import com.timeoutairline.epiline.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "*")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * GET /api/airports - Get all airports
     */

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    /**
     * GET /api/airports/{id} - Get airport by ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Optional<Airport> airport = airportService.getAirportById(id);
        return airport.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/airports/country/{country} - Get airports by country
     */

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Airport>> getAirportsByCountry(@PathVariable String country) {
        List<Airport> airports = airportService.getAirportsByCountry(country);
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    /**
     * GET /api/airports/city/{city} - Get airports by city
     */

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Airport>> getAirportsByCity(@PathVariable String city) {
        List<Airport> airports = airportService.getAirportsByCity(city);
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    /**
     * GET /api/airports/search?name={name} - Search airports by name
     */

    @GetMapping("/search")
    public ResponseEntity<List<Airport>> searchAirportsByName(@RequestParam String name) {
        List<Airport> airports = airportService.searchAirportsByName(name);
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    /**
     * POST /api/airports - Create new airport
     */

@PostMapping
public ResponseEntity<?> createAirport(@RequestBody Airport airport) {
    // Check if airport ID already exists
    if (airport.getAirportId() != null && airportService.getAirportById(airport.getAirportId()).isPresent()) {
        return new ResponseEntity<>("Airport with this ID already exists", HttpStatus.CONFLICT);
    }

    // Validate required fields
    if (airport.getName() == null || airport.getName().isEmpty()) {
        return new ResponseEntity<>("Airport name is required", HttpStatus.BAD_REQUEST);
    }

    if (airport.getCountry() == null || airport.getCountry().isEmpty()) {
        return new ResponseEntity<>("Country is required", HttpStatus.BAD_REQUEST);
    }

    if (airport.getCity() == null || airport.getCity().isEmpty()) {
        return new ResponseEntity<>("City is required", HttpStatus.BAD_REQUEST);
    }

    Airport createdAirport = airportService.saveAirport(airport);
    return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
}

    /**
     * PUT /api/airports/{id} - Update existing airport
     */
    
@PutMapping("/{id}")
public ResponseEntity<?> updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
    // Check if airport exists
    Optional<Airport> existingAirport = airportService.getAirportById(id);
    if (existingAirport.isEmpty()) {
        return new ResponseEntity<>("Airport not found", HttpStatus.NOT_FOUND);
    }

    // Validate other required fields
    if (updatedAirport.getName() == null || updatedAirport.getName().isEmpty()) {
        return new ResponseEntity<>("Airport name is required", HttpStatus.BAD_REQUEST);
    }

    if (updatedAirport.getCountry() == null || updatedAirport.getCountry().isEmpty()) {
        return new ResponseEntity<>("Country is required", HttpStatus.BAD_REQUEST);
    }

    if (updatedAirport.getCity() == null || updatedAirport.getCity().isEmpty()) {
        return new ResponseEntity<>("City is required", HttpStatus.BAD_REQUEST);
    }

    Airport airport = airportService.updateAirport(id, updatedAirport);
    return new ResponseEntity<>(airport, HttpStatus.OK);
}

    /**
     * DELETE /api/airports/{id} - Delete airport
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable Long id) {
        if (airportService.deleteAirport(id)) {
            return new ResponseEntity<>("Airport deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Airport not found", HttpStatus.NOT_FOUND);
        }
    }
}