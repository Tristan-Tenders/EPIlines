package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookRepository - Data access layer for Book (Reservation) entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Find reservations by flight number
    List<Book> findByFlightFlightNum(Long flightNum);

    // Find reservations by client ID
    List<Book> findByClientClientId(Long clientId);

    // Find reservations by seat type
    List<Book> findByTypeSeat(String typeSeat);

    // Find reservations by flight number and client
    List<Book> findByFlightFlightNumAndClientClientId(Long flightNum, Long clientId);

    // Check if reservation exists by flight number and client
    boolean existsByFlightFlightNumAndClientClientId(Long flightNum, Long clientId);
}