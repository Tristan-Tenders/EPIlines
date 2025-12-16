package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.MilesReward;
import com.timeoutairline.epiline.service.MilesRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * MilesRewardController - REST API endpoints for MilesReward management
 * Matches the style of ClientController in your project
 */
@RestController
@RequestMapping("/api/milesrewards")
@CrossOrigin(origins = "*")
public class MilesRewardController {

    private final MilesRewardService milesRewardService;

    @Autowired
    public MilesRewardController(MilesRewardService milesRewardService) {
        this.milesRewardService = milesRewardService;
    }

    /**
     * GET /api/milesrewards - Get all miles rewards
     */
    @GetMapping
    public ResponseEntity<List<MilesReward>> getAllMilesRewards() {
        List<MilesReward> milesRewards = milesRewardService.getAllMilesRewards();
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * GET /api/milesrewards/{id} - Get miles reward by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MilesReward> getMilesRewardById(@PathVariable Long id) {
        Optional<MilesReward> milesReward = milesRewardService.getMilesRewardById(id);
        return milesReward.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/milesrewards/client/{clientId} - Get miles rewards by client ID
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<MilesReward>> getMilesRewardsByClientId(@PathVariable Long clientId) {
        List<MilesReward> milesRewards = milesRewardService.getMilesRewardsByClientId(clientId);
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * GET /api/milesrewards/flight/{flightNum} - Get miles rewards by flight number
     */
    @GetMapping("/flight/{flightNum}")
    public ResponseEntity<List<MilesReward>> getMilesRewardsByFlightNum(@PathVariable Long flightNum) {
        List<MilesReward> milesRewards = milesRewardService.getMilesRewardsByFlightNum(flightNum);
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * GET /api/milesrewards/date/{date} - Get miles rewards by date
     */
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MilesReward>> getMilesRewardsByDate(@PathVariable LocalDate date) {
        List<MilesReward> milesRewards = milesRewardService.getMilesRewardsByDate(date);
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * GET /api/milesrewards/search/client-flight?clientId={clientId}&flightNum={flightNum} - Search by client and flight
     */

    @GetMapping("/search/client-flight")
    public ResponseEntity<List<MilesReward>> getMilesRewardsByClientAndFlight(
            @RequestParam Long clientId, @RequestParam Long flightNum) {
        List<MilesReward> milesRewards = milesRewardService.getMilesRewardsByClientAndFlight(clientId, flightNum);
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * GET /api/milesrewards/search/client-date?clientId={clientId}&date={date} - Search by client and date
     */
    @GetMapping("/search/client-date")
    public ResponseEntity<List<MilesReward>> getMilesRewardsByClientAndDate(
            @RequestParam Long clientId, @RequestParam LocalDate date) {
        List<MilesReward> milesRewards = milesRewardService.getMilesRewardsByClientAndDate(clientId, date);
        return new ResponseEntity<>(milesRewards, HttpStatus.OK);
    }

    /**
     * POST /api/milesrewards - Create new miles reward
     */
    @PostMapping
    public ResponseEntity<?> createMilesReward(@RequestBody MilesReward milesReward) {
        // Validate client
        if (milesReward.getClient() == null) {
            return new ResponseEntity<>("Client is required", HttpStatus.BAD_REQUEST);
        }

        // Validate flight
        if (milesReward.getFlight() == null) {
            return new ResponseEntity<>("Flight is required", HttpStatus.BAD_REQUEST);
        }

        // Validate date
        if (milesReward.getDate() == null) {
            return new ResponseEntity<>("Date is required", HttpStatus.BAD_REQUEST);
        }

        // Check if miles reward already exists for this client and flight
        if (milesRewardService.milesRewardExists(milesReward.getClient().getClientId(), milesReward.getFlight().getFlightNum())) {
            return new ResponseEntity<>("Miles reward already exists for this client and flight", HttpStatus.CONFLICT);
        }

        MilesReward createdMilesReward = milesRewardService.saveMilesReward(milesReward);
        return new ResponseEntity<>(createdMilesReward, HttpStatus.CREATED);
    }

    /**
     * PUT /api/milesrewards/{id} - Update existing miles reward
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMilesReward(@PathVariable Long id, @RequestBody MilesReward updatedMilesReward) {
        // Check if miles reward exists
        Optional<MilesReward> existingMilesReward = milesRewardService.getMilesRewardById(id);
        if (existingMilesReward.isEmpty()) {
            return new ResponseEntity<>("Miles reward not found", HttpStatus.NOT_FOUND);
        }

        // Validate client
        if (updatedMilesReward.getClient() == null) {
            return new ResponseEntity<>("Client is required", HttpStatus.BAD_REQUEST);
        }

        // Validate flight
        if (updatedMilesReward.getFlight() == null) {
            return new ResponseEntity<>("Flight is required", HttpStatus.BAD_REQUEST);
        }

        // Validate date
        if (updatedMilesReward.getDate() == null) {
            return new ResponseEntity<>("Date is required", HttpStatus.BAD_REQUEST);
        }

        MilesReward milesReward = milesRewardService.updateMilesReward(id, updatedMilesReward);
        return new ResponseEntity<>(milesReward, HttpStatus.OK);
    }

    /**
     * DELETE /api/milesrewards/{id} - Delete miles reward
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMilesReward(@PathVariable Long id) {
        if (milesRewardService.deleteMilesReward(id)) {
            return new ResponseEntity<>("Miles reward deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Miles reward not found", HttpStatus.NOT_FOUND);
        }
    }
}