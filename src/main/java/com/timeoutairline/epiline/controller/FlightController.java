package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Flight;
import com.timeoutairline.epiline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * FlightController - REST API endpoints for Flight management
 * Matches the style of UserController in your project
 */
@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * GET /api/flights - Get all flights
     */
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/{flightNum} - Get flight by flight number
     */
    @GetMapping("/{flightNum}")
    public ResponseEntity<Flight> getFlightByNum(@PathVariable Long flightNum) {
        Optional<Flight> flight = flightService.getFlightByNum(flightNum);
        return flight.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/flights/departure/{city} - Get flights by departure city
     */
    @GetMapping("/departure/{city}")
    public ResponseEntity<List<Flight>> getFlightsByDepCity(@PathVariable String city) {
        List<Flight> flights = flightService.getFlightsByDepCity(city);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/arrival/{city} - Get flights by arrival city
     */
    @GetMapping("/arrival/{city}")
    public ResponseEntity<List<Flight>> getFlightsByArrCity(@PathVariable String city) {
        List<Flight> flights = flightService.getFlightsByArrCity(city);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/route?from={depCity}&to={arrCity} - Get flights by route
     */
    @GetMapping("/route")
    public ResponseEntity<List<Flight>> getFlightsByRoute(
            @RequestParam String from,
            @RequestParam String to) {
        List<Flight> flights = flightService.getFlightsByRoute(from, to);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/airport/departure/{airportId} - Get flights by departure airport
     */
    @GetMapping("/airport/departure/{airportId}")
    public ResponseEntity<List<Flight>> getFlightsByDepAirport(@PathVariable Long airportId) {
        List<Flight> flights = flightService.getFlightsByDepAirport(airportId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/airport/arrival/{airportId} - Get flights by arrival airport
     */
    @GetMapping("/airport/arrival/{airportId}")
    public ResponseEntity<List<Flight>> getFlightsByArrAirport(@PathVariable Long airportId) {
        List<Flight> flights = flightService.getFlightsByArrAirport(airportId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/plane/{planeId} - Get flights by plane
     */
    @GetMapping("/plane/{planeId}")
    public ResponseEntity<List<Flight>> getFlightsByPlane(@PathVariable Long planeId) {
        List<Flight> flights = flightService.getFlightsByPlane(planeId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/status/{status} - Get flights by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Flight>> getFlightsByStatus(@PathVariable String status) {
        List<Flight> flights = flightService.getFlightsByStatus(status);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/upcoming - Get upcoming flights
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<Flight>> getUpcomingFlights() {
        List<Flight> flights = flightService.getUpcomingFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * GET /api/flights/available - Get available flights (with seats)
     */
    @GetMapping("/available")
    public ResponseEntity<List<Flight>> getAvailableFlights() {
        List<Flight> flights = flightService.getAvailableFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    /**
     * POST /api/flights - Create new flight
     */
    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody Flight flight) {
        // Validate required fields
        if (flight.getDepCity() == null || flight.getArrCity() == null) {
            return new ResponseEntity<>("Departure and arrival cities are required", HttpStatus.BAD_REQUEST);
        }

        if (flight.getDepTime() == null || flight.getArrTime() == null) {
            return new ResponseEntity<>("Departure and arrival times are required", HttpStatus.BAD_REQUEST);
        }

        if (flight.getDepAirportId() == null || flight.getArrAirportId() == null) {
            return new ResponseEntity<>("Departure and arrival airport IDs are required", HttpStatus.BAD_REQUEST);
        }

        if (flight.getPlaneId() == null) {
            return new ResponseEntity<>("Plane ID is required", HttpStatus.BAD_REQUEST);
        }

        if (flight.getTotalSeats() == null || flight.getTotalSeats() <= 0) {
            return new ResponseEntity<>("Total seats must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        // Validate departure time is before arrival time
        if (flight.getDepTime().isAfter(flight.getArrTime())) {
            return new ResponseEntity<>("Departure time must be before arrival time", HttpStatus.BAD_REQUEST);
        }

        Flight createdFlight = flightService.saveFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    /**
     * PUT /api/flights/{flightNum} - Update existing flight
     */
    @PutMapping("/{flightNum}")
    public ResponseEntity<?> updateFlight(@PathVariable Long flightNum, @RequestBody Flight updatedFlight) {
        // Check if flight exists
        Optional<Flight> existingFlight = flightService.getFlightByNum(flightNum);
        if (existingFlight.isEmpty()) {
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }

        // Validate departure time is before arrival time if both are provided
        if (updatedFlight.getDepTime() != null && updatedFlight.getArrTime() != null) {
            if (updatedFlight.getDepTime().isAfter(updatedFlight.getArrTime())) {
                return new ResponseEntity<>("Departure time must be before arrival time", HttpStatus.BAD_REQUEST);
            }
        }

        Flight flight = flightService.updateFlight(flightNum, updatedFlight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    /**
     * PATCH /api/flights/{flightNum}/status - Update flight status
     */
    @PatchMapping("/{flightNum}/status")
    public ResponseEntity<?> updateFlightStatus(
            @PathVariable Long flightNum,
            @RequestParam String status) {

        // Validate status
        String[] validStatuses = {"SCHEDULED", "BOARDING", "DEPARTED", "ARRIVED", "CANCELLED", "DELAYED"};
        boolean isValid = false;
        for (String validStatus : validStatuses) {
            if (validStatus.equalsIgnoreCase(status)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            return new ResponseEntity<>(
                    "Invalid status. Valid statuses: SCHEDULED, BOARDING, DEPARTED, ARRIVED, CANCELLED, DELAYED",
                    HttpStatus.BAD_REQUEST);
        }

        Flight flight = flightService.updateFlightStatus(flightNum, status);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /api/flights/{flightNum}/book - Book a seat on a flight
     */
    @PostMapping("/{flightNum}/book")
    public ResponseEntity<?> bookSeat(@PathVariable Long flightNum) {
        if (!flightService.hasAvailableSeats(flightNum)) {
            return new ResponseEntity<>("No available seats on this flight", HttpStatus.CONFLICT);
        }

        Flight flight = flightService.bookSeat(flightNum);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /api/flights/{flightNum}/cancel-seat - Cancel a seat booking
     */
    @PostMapping("/{flightNum}/cancel-seat")
    public ResponseEntity<?> cancelSeat(@PathVariable Long flightNum) {
        Flight flight = flightService.cancelSeat(flightNum);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight not found or all seats available", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /api/flights/{flightNum} - Delete flight
     */
    @DeleteMapping("/{flightNum}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long flightNum) {
        if (flightService.deleteFlight(flightNum)) {
            return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }
    }
}