package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Find client by passport number
    Optional<Client> findByNumPassport(Long numPassport);

    // FIXED: Traverse the 'user' relationship to its 'id' field
    Optional<Client> findByUser_Id(Long userId);

    // Find clients by user first name (partial match)
    List<Client> findByUserFirstnameContainingIgnoreCase(String firstname);

    // Find clients by user last name (partial match)
    List<Client> findByUserLastnameContainingIgnoreCase(String lastname);

    // Find client by user email
    Optional<Client> findByUserEmail(String email);

    // Check if passport number exists
    boolean existsByNumPassport(Long numPassport);

    // FIXED: Same traversal for exists
    boolean existsByUser_Id(Long userId);
}