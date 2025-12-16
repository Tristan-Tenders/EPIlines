package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Book;
import com.timeoutairline.epiline.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET /api/reservations - Get all reservations
     */

    @GetMapping
    public ResponseEntity<List<Book>> getAllReservations() {
        List<Book> reservations = bookService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    /**
     * GET /api/reservations/{id} - Get reservation by ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<Book> getReservationById(@PathVariable Long id) {
        Optional<Book> reservation = bookService.getReservationById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/reservations/flight/{flightId} - Get reservations by flight ID
     */

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Book>> getReservationsByFlightId(@PathVariable Long flightId) {
        List<Book> reservations = bookService.getReservationsByFlightId(flightId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    /**
     * GET /api/reservations/client/{clientId} - Get reservations by client ID
     */

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Book>> getReservationsByClientId(@PathVariable Long clientId) {
        List<Book> reservations = bookService.getReservationsByClientId(clientId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    /**
     * GET /api/reservations/seat/{typeSeat} - Get reservations by seat type
     */

    @GetMapping("/seat/{typeSeat}")
    public ResponseEntity<List<Book>> getReservationsByTypeSeat(@PathVariable String typeSeat) {
        List<Book> reservations = bookService.getReservationsByTypeSeat(typeSeat);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    /**
     * POST /api/reservations - Create new reservation
     */

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Book reservation) {
        // Validate required fields
        if (reservation.getFlightNum() == null) {
            return new ResponseEntity<>("Flight ID is required", HttpStatus.BAD_REQUEST);
        }

        if (reservation.getClientId() == null) {
            return new ResponseEntity<>("Client ID is required", HttpStatus.BAD_REQUEST);
        }

        if (reservation.getTypeSeat() == null || reservation.getTypeSeat().isEmpty()) {
            return new ResponseEntity<>("Seat type is required", HttpStatus.BAD_REQUEST);
        }

        Book createdReservation = bookService.saveReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    /**
     * PUT /api/reservations/{id} - Update existing reservation
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody Book updatedReservation) {
        // Check if reservation exists
        Optional<Book> existingReservation = bookService.getReservationById(id);
        if (existingReservation.isEmpty()) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }

        // Validate required fields
        if (updatedReservation.getFlight().getFlightNum() == null) {
            return new ResponseEntity<>("Flight number is required", HttpStatus.BAD_REQUEST);
        }

        if (updatedReservation.getClient().getClientId() == null) {
            return new ResponseEntity<>("Client ID is required", HttpStatus.BAD_REQUEST);
        }

        if (updatedReservation.getTypeSeat() == null || updatedReservation.getTypeSeat().isEmpty()) {
            return new ResponseEntity<>("Seat type is required", HttpStatus.BAD_REQUEST);
        }

        Book reservation = bookService.updateReservation(id, updatedReservation);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    /**
     * DELETE /api/reservations/{id} - Delete reservation
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        if (bookService.deleteReservation(id)) {
            return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }
}