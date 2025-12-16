package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Book;
import com.timeoutairline.epiline.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * BookService - Business logic layer for Book (Reservation) operations
 * Matches the style of AirportService in your project
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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