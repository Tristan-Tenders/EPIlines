package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Book;
import com.timeoutairline.epiline.model.Flight;
import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.repository.BookRepository;
import com.timeoutairline.epiline.repository.FlightRepository;
import com.timeoutairline.epiline.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final FlightRepository flightRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       FlightRepository flightRepository,
                       ClientRepository clientRepository) {
        this.bookRepository = bookRepository;
        this.flightRepository = flightRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Get all reservations
     */

    public List<Book> getAllReservations() {
        return bookRepository.findAll();
    }

    /**
     * Get reservation by ID
     */

    public Optional<Book> getReservationById(Long reservationId) {
        return bookRepository.findById(reservationId);
    }

    /**
     * Get reservations by flight ID
     */

    public List<Book> getReservationsByFlightId(Long flightId) {
        return bookRepository.findByFlightFlightNum(flightId);
    }

    /**
     * Get reservations by client ID
     */

    public List<Book> getReservationsByClientId(Long clientId) {
        return bookRepository.findByClientClientId(clientId);
    }

    /**
     * Get reservations by seat type
     */

    public List<Book> getReservationsByTypeSeat(String typeSeat) {
        return bookRepository.findByTypeSeat(typeSeat);
    }

    /**
     * Get reservations by flight and client
     */

    public List<Book> getReservationsByFlightAndClient(Long flightId, Long clientId) {
        return bookRepository.findByFlightFlightNumAndClientClientId(flightId, clientId);
    }

    /**
     * Save a new reservation
     */

    public Book saveReservation(Book reservation) {
        // Load the real managed Flight and Client entities from DB
        Flight managedFlight = flightRepository.findById(reservation.getFlight().getFlightNum())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + reservation.getFlight().getFlightNum()));

        Client managedClient = clientRepository.findById(reservation.getClient().getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + reservation.getClient().getClientId()));

        reservation.setFlight(managedFlight);
        reservation.setClient(managedClient);

        return bookRepository.save(reservation);
    }

    /**
     * Update existing reservation
     * Returns null if reservation doesn't exist
     */
    public Book updateReservation(Long reservationId, Book updatedReservation) {
        if (bookRepository.existsById(reservationId)) {
            updatedReservation.setReservationId(reservationId);
            return bookRepository.save(updatedReservation);
        }
        return null;
    }

    /**
     * Delete reservation by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteReservation(Long reservationId) {
        if (bookRepository.existsById(reservationId)) {
            bookRepository.deleteById(reservationId);
            return true;
        }
        return false;
    }

    /**
     * Check if reservation exists by flight and client
     */
    public boolean reservationExists(Long flightId, Long clientId) {
        return bookRepository.existsByFlightFlightNumAndClientClientId(flightId, clientId);
    }
}