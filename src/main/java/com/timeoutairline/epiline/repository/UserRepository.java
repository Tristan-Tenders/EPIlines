package com.timeoutairline.epiline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository - Data access layer for User entity
 * Extends JpaRepository to get CRUD operations for free
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email address
     * Spring Data JPA automatically implements this based on method name
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if email already exists
     */
    boolean existsByEmail(String email);
}
