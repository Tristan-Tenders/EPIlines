package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.MilesReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * MilesRewardRepository - Data access layer for MilesReward entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {
    // Find miles reward by client ID
    List<MilesReward> findByClientClientId(Long clientId);

    // Find miles reward by flight number
    List<MilesReward> findByFlightFlightNum(Long flightNum);

    // Find miles reward by date
    List<MilesReward> findByDate(LocalDate date);

    // Find miles rewards by client and flight
    List<MilesReward> findByClientClientIdAndFlightFlightNum(Long clientId, Long flightNum);

    // Find miles reward by client and date
    List<MilesReward> findByClientClientIdAndDate(Long clientId, LocalDate date);

    // Find miles reward by flight and date
    List<MilesReward> findByFlightFlightNumAndDate(Long flightNum, LocalDate date);

    // Check if miles reward exists by client and flight
    boolean existsByClientClientIdAndFlightFlightNum(Long clientId, Long flightNum);

    // Count miles rewards by client
    long countByClientClientId(Long clientId);
}