package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ClientRepository - Data access layer for Client entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Find client by passport number
    Optional<Client> findByNumPassport(Long numPassport);

    // Find client by user ID
    Optional<Client> findByUserId(Long userId);

    // Find clients by user first name (partial match)
    List<Client> findByUserFirstnameContainingIgnoreCase(String firstname);

    // Find clients by user last name (partial match)
    List<Client> findByUserLastnameContainingIgnoreCase(String lastname);

    // Find client by user email
    Optional<Client> findByUserEmail(String email);

    // Check if passport number exists
    boolean existsByNumPassport(Long numPassport);

    // Check if client exists by user ID
    boolean existsByUserId(Long userId);
}