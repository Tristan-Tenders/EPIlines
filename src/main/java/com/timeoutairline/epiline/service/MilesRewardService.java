package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.model.DiscountCode;
import com.timeoutairline.epiline.model.Flight;
import com.timeoutairline.epiline.model.MilesReward;
import com.timeoutairline.epiline.repository.ClientRepository;
import com.timeoutairline.epiline.repository.FlightRepository;
import com.timeoutairline.epiline.repository.MilesRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class MilesRewardService {
    private final MilesRewardRepository milesRewardRepository;
    private final ClientRepository clientRepository;
    private final FlightRepository flightRepository;
    private final DiscountCodeService discountCodeService;

    @Autowired
    public MilesRewardService(MilesRewardRepository milesRewardRepository,
                              ClientRepository clientRepository,
                              FlightRepository flightRepository,
                              DiscountCodeService discountCodeService) {
        this.milesRewardRepository = milesRewardRepository;
        this.clientRepository = clientRepository;
        this.flightRepository = flightRepository;
        this.discountCodeService = discountCodeService;
    }

    public List<MilesReward> getAllMilesRewards() {
        return milesRewardRepository.findAll();
    }

    public Optional<MilesReward> getMilesRewardById(Long rewardId) {
        return milesRewardRepository.findById(rewardId);
    }

    public List<MilesReward> getMilesRewardsByClientId(Long clientId) {
        return milesRewardRepository.findByClientClientId(clientId);
    }

    public List<MilesReward> getMilesRewardsByFlightNum(Long flightNum) {
        return milesRewardRepository.findByFlightFlightNum(flightNum);
    }

    public List<MilesReward> getMilesRewardsByDate(LocalDate date) {
        return milesRewardRepository.findByDate(date);
    }

    public List<MilesReward> getMilesRewardsByClientAndFlight(Long clientId, Long flightNum) {
        return milesRewardRepository.findByClientClientIdAndFlightFlightNum(clientId, flightNum);
    }

    public List<MilesReward> getMilesRewardsByClientAndDate(Long clientId, LocalDate date) {
        return milesRewardRepository.findByClientClientIdAndDate(clientId, date);
    }

    public List<MilesReward> getMilesRewardsByFlightAndDate(Long flightNum, LocalDate date) {
        return milesRewardRepository.findByFlightFlightNumAndDate(flightNum, date);
    }

    public MilesReward saveMilesReward(MilesReward milesReward) {
        // Load real Client
        Client realClient = clientRepository.findById(milesReward.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found: " + milesReward.getClientId()));
        milesReward.setClient(realClient);

        // Load real Flight
        Flight realFlight = flightRepository.findById(milesReward.getFlightNum())
                .orElseThrow(() -> new RuntimeException("Flight not found: " + milesReward.getFlightNum()));
        milesReward.setFlight(realFlight);

        // Save the miles reward
        MilesReward savedReward = milesRewardRepository.save(milesReward);

        // Check if client qualifies for discount code and generate if eligible
        Integer year = milesReward.getDate().getYear();
        DiscountCode generatedCode = discountCodeService.checkAndGenerateDiscountCode(
                milesReward.getClientId(), year);

        if (generatedCode != null) {
            System.out.println("🎉 Discount code generated for client " + milesReward.getClientId() +
                    ": " + generatedCode.getCode());
        }

        return savedReward;
    }

    public MilesReward updateMilesReward(Long rewardId, MilesReward updatedMilesReward) {
        if (milesRewardRepository.existsById(rewardId)) {
            updatedMilesReward.setRewardId(rewardId);
            return milesRewardRepository.save(updatedMilesReward);
        }
        return null;
    }

    public boolean deleteMilesReward(Long rewardId) {
        if (milesRewardRepository.existsById(rewardId)) {
            milesRewardRepository.deleteById(rewardId);
            return true;
        }
        return false;
    }

    public boolean milesRewardExists(Long clientId, Long flightNum) {
        return milesRewardRepository.existsByClientClientIdAndFlightFlightNum(clientId, flightNum);
    }

    public long countMilesRewardsByClient(Long clientId) {
        return milesRewardRepository.countByClientClientId(clientId);
    }
}