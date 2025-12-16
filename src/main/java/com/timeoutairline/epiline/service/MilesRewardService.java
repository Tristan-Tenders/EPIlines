package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.MilesReward;
import com.timeoutairline.epiline.repository.MilesRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * MilesRewardService - Business logic layer for MilesReward operations
 * Matches the style of ClientService in your project
 */
@Service
public class MilesRewardService {
    private final MilesRewardRepository milesRewardRepository;

    @Autowired
    public MilesRewardService(MilesRewardRepository milesRewardRepository) {
        this.milesRewardRepository = milesRewardRepository;
    }

    /**
     * Get all miles rewards
     */
    public List<MilesReward> getAllMilesRewards() {
        return milesRewardRepository.findAll();
    }

    /**
     * Get miles reward by ID
     */
    public Optional<MilesReward> getMilesRewardById(Long rewardId) {
        return milesRewardRepository.findById(rewardId);
    }

    /**
     * Get miles rewards by client ID
     */
    public List<MilesReward> getMilesRewardsByClientId(Long clientId) {
        return milesRewardRepository.findByClientClientId(clientId);
    }

    /**
     * Get miles rewards by flight number
     */
    public List<MilesReward> getMilesRewardsByFlightNum(Long flightNum) {
        return milesRewardRepository.findByFlightFlightNum(flightNum);
    }

    /**
     * Get miles rewards by date
     */
    public List<MilesReward> getMilesRewardsByDate(LocalDate date) {
        return milesRewardRepository.findByDate(date);
    }

    /**
     * Get miles rewards by client and flight
     */
    public List<MilesReward> getMilesRewardsByClientAndFlight(Long clientId, Long flightNum) {
        return milesRewardRepository.findByClientClientIdAndFlightFlightNum(clientId, flightNum);
    }

    /**
     * Get miles rewards by client and date
     */
    public List<MilesReward> getMilesRewardsByClientAndDate(Long clientId, LocalDate date) {
        return milesRewardRepository.findByClientClientIdAndDate(clientId, date);
    }

    /**
     * Get miles rewards by flight and date
     */
    public List<MilesReward> getMilesRewardsByFlightAndDate(Long flightNum, LocalDate date) {
        return milesRewardRepository.findByFlightFlightNumAndDate(flightNum, date);
    }

    /**
     * Save a new miles reward
     */
    public MilesReward saveMilesReward(MilesReward milesReward) {
        return milesRewardRepository.save(milesReward);
    }

    /**
     * Update existing miles reward
     * Returns null if miles reward doesn't exist
     */
    public MilesReward updateMilesReward(Long rewardId, MilesReward updatedMilesReward) {
        if (milesRewardRepository.existsById(rewardId)) {
            updatedMilesReward.setRewardId(rewardId);
            return milesRewardRepository.save(updatedMilesReward);
        }
        return null;
    }

    /**
     * Delete miles reward by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteMilesReward(Long rewardId) {
        if (milesRewardRepository.existsById(rewardId)) {
            milesRewardRepository.deleteById(rewardId);
            return true;
        }
        return false;
    }

    /**
     * Check if miles reward exists by client and flight
     */
    public boolean milesRewardExists(Long clientId, Long flightNum) {
        return milesRewardRepository.existsByClientClientIdAndFlightFlightNum(clientId, flightNum);
    }

    /**
     * Count miles rewards by client
     */
    public long countMilesRewardsByClient(Long clientId) {
        return milesRewardRepository.countByClientClientId(clientId);
    }
}