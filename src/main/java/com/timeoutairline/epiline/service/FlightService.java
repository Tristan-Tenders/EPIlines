package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Flight;
import com.timeoutairline.epiline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Get all flights
     */
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Get flight by flight number
     */
    public Optional<Flight> getFlightByNum(Long flightNum) {
        return flightRepository.findById(flightNum);
    }

    /**
     * Get flights by departure city
     */
    public List<Flight> getFlightsByDepCity(String depCity) {
        return flightRepository.findByDepCity(depCity);
    }

    /**
     * Get flights by arrival city
     */
    public List<Flight> getFlightsByArrCity(String arrCity) {
        return flightRepository.findByArrCity(arrCity);
    }

    /**
     * Get flights by departure and arrival cities
     */
    public List<Flight> getFlightsByRoute(String depCity, String arrCity) {
        return flightRepository.findByDepCityAndArrCity(depCity, arrCity);
    }

    /**
     * Get flights by departure airport
     */
    public List<Flight> getFlightsByDepAirport(Long depAirportId) {
        return flightRepository.findByDepAirportId(depAirportId);
    }

    /**
     * Get flights by arrival airport
     */
    public List<Flight> getFlightsByArrAirport(Long arrAirportId) {
        return flightRepository.findByArrAirportId(arrAirportId);
    }

    /**
     * Get flights by plane
     */
    public List<Flight> getFlightsByPlane(Long planeId) {
        return flightRepository.findByPlaneId(planeId);
    }

    /**
     * Get flights by status
     */
    public List<Flight> getFlightsByStatus(String status) {
        return flightRepository.findByStatus(status.toUpperCase());
    }

    /**
     * Get upcoming flights (departing after now)
     */
    public List<Flight> getUpcomingFlights() {
        return flightRepository.findByDepTimeAfter(LocalDateTime.now());
    }

    /**
     * Get flights departing between two times
     */
    public List<Flight> getFlightsByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return flightRepository.findByDepTimeBetween(startTime, endTime);
    }

    /**
     * Get available flights (with available seats)
     */
    public List<Flight> getAvailableFlights() {
        return flightRepository.findByAvailableSeatsGreaterThan(0);
    }

    /**
     * Save a new flight
     */
    public Flight saveFlight(Flight flight) {
        // Set status to SCHEDULED if not provided
        if (flight.getStatus() == null || flight.getStatus().isEmpty()) {
            flight.setStatus("SCHEDULED");
        } else {
            flight.setStatus(flight.getStatus().toUpperCase());
        }

        // Initialize available seats to total seats if not set
        if (flight.getAvailableSeats() == null) {
            flight.setAvailableSeats(flight.getTotalSeats());
        }

        return flightRepository.save(flight);
    }

    /**
     * Update existing flight
     * Returns null if flight doesn't exist
     */
    public Flight updateFlight(Long flightNum, Flight updatedFlight) {
        if (flightRepository.existsById(flightNum)) {
            updatedFlight.setFlightNum(flightNum);

            // Normalize status to uppercase
            if (updatedFlight.getStatus() != null) {
                updatedFlight.setStatus(updatedFlight.getStatus().toUpperCase());
            }

            return flightRepository.save(updatedFlight);
        }
        return null;
    }

    /**
     * Update flight status
     */
    public Flight updateFlightStatus(Long flightNum, String status) {
        Optional<Flight> flightOpt = flightRepository.findById(flightNum);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            flight.setStatus(status.toUpperCase());
            return flightRepository.save(flight);
        }
        return null;
    }

    /**
     * Book a seat (decrease available seats)
     */
    public Flight bookSeat(Long flightNum) {
        Optional<Flight> flightOpt = flightRepository.findById(flightNum);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.getAvailableSeats() > 0) {
                flight.setAvailableSeats(flight.getAvailableSeats() - 1);
                return flightRepository.save(flight);
            }
        }
        return null;
    }

    /**
     * Cancel a seat (increase available seats)
     */
    public Flight cancelSeat(Long flightNum) {
        Optional<Flight> flightOpt = flightRepository.findById(flightNum);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.getAvailableSeats() < flight.getTotalSeats()) {
                flight.setAvailableSeats(flight.getAvailableSeats() + 1);
                return flightRepository.save(flight);
            }
        }
        return null;
    }

    /**
     * Delete flight by flight number
     * Returns true if deleted, false if not found
     */
    public boolean deleteFlight(Long flightNum) {
        if (flightRepository.existsById(flightNum)) {
            flightRepository.deleteById(flightNum);
            return true;
        }
        return false;
    }

    /**
     * Check if flight has available seats
     */
    public boolean hasAvailableSeats(Long flightNum) {
        Optional<Flight> flightOpt = flightRepository.findById(flightNum);
        return flightOpt.map(flight -> flight.getAvailableSeats() > 0).orElse(false);
    }
}