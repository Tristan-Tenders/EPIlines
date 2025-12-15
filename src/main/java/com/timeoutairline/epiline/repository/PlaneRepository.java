package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * PlaneRepository - Data access layer for Plane entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {

    // Find all planes by brand (e.g., "Boeing", "Airbus")
    List<Plane> findByBrand(String brand);

    // Find planes by model (e.g., "737-800", "A320")
    List<Plane> findByModel(String model);

    // Find planes manufactured in a specific year
    List<Plane> findByManYear(Integer manYear);

    // Find a specific plane by brand and model combination
    Optional<Plane> findByBrandAndModel(String brand, String model);

    // Check if a plane with specific brand and model exists
    boolean existsByBrandAndModel(String brand, String model);
}