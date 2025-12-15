package com.timeoutairline.epiline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timeoutairline.epiline.dtos.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByName(String name);
    Airport findByCity(String city);
}