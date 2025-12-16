package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.model.DiscountCode;
import com.timeoutairline.epiline.repository.ClientRepository;
import com.timeoutairline.epiline.repository.DiscountCodeRepository;
import com.timeoutairline.epiline.repository.MilesRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final ClientRepository clientRepository;
    private final MilesRewardRepository milesRewardRepository;

    @Autowired
    public DiscountCodeService(DiscountCodeRepository discountCodeRepository,
                               ClientRepository clientRepository,
                               MilesRewardRepository milesRewardRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.clientRepository = clientRepository;
        this.milesRewardRepository = milesRewardRepository;
    }

    /**
     * Check if client qualifies for discount code and generate if eligible
     * Called after each flight booking
     */
    public DiscountCode checkAndGenerateDiscountCode(Long clientId, Integer year) {
        // Check if client already has a discount code for this year
        if (discountCodeRepository.existsByClientClientIdAndYear(clientId, year)) {
            return null; // Already has discount code for this year
        }

        // Count flights for this client in the specified year
        long flightCount = milesRewardRepository.findByClientClientId(clientId)
                .stream()
                .filter(reward -> reward.getDate().getYear() == year)
                .count();

        // Generate discount code if client has 3 or more flights
        if (flightCount >= 3) {
            return generateDiscountCode(clientId, year);
        }

        return null;
    }

    /**
     * Generate a random discount code
     */
    private DiscountCode generateDiscountCode(Long clientId, Integer year) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));

        String code = generateRandomCode();

        // Ensure code is unique
        while (discountCodeRepository.existsByCode(code)) {
            code = generateRandomCode();
        }

        DiscountCode discountCode = new DiscountCode();
        discountCode.setClient(client);
        discountCode.setCode(code);
        discountCode.setYear(year);
        discountCode.setGeneratedAt(LocalDateTime.now());
        discountCode.setIsUsed(false);
        discountCode.setDiscountPercentage(10.0f); // 10% discount

        return discountCodeRepository.save(discountCode);
    }

    /**
     * Generate a random 10-character alphanumeric code
     */
    private String generateRandomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }

    /**
     * Get all discount codes
     */
    public List<DiscountCode> getAllDiscountCodes() {
        return discountCodeRepository.findAll();
    }

    /**
     * Get discount code by ID
     */
    public Optional<DiscountCode> getDiscountCodeById(Long discountId) {
        return discountCodeRepository.findById(discountId);
    }

    /**
     * Get discount codes by client ID
     */
    public List<DiscountCode> getDiscountCodesByClientId(Long clientId) {
        return discountCodeRepository.findByClientClientId(clientId);
    }

    /**
     * Get discount code by code string
     */
    public Optional<DiscountCode> getDiscountCodeByCode(String code) {
        return discountCodeRepository.findByCode(code);
    }

    /**
     * Get discount codes by year
     */
    public List<DiscountCode> getDiscountCodesByYear(Integer year) {
        return discountCodeRepository.findByYear(year);
    }

    /**
     * Get unused discount codes by client
     */
    public List<DiscountCode> getUnusedDiscountCodesByClient(Long clientId) {
        return discountCodeRepository.findByClientClientIdAndIsUsed(clientId, false);
    }

    /**
     * Mark discount code as used
     */
    public DiscountCode useDiscountCode(String code) {
        Optional<DiscountCode> discountCodeOpt = discountCodeRepository.findByCode(code);
        if (discountCodeOpt.isPresent()) {
            DiscountCode discountCode = discountCodeOpt.get();
            if (!discountCode.getIsUsed()) {
                discountCode.setIsUsed(true);
                discountCode.setUsedAt(LocalDateTime.now());
                return discountCodeRepository.save(discountCode);
            }
        }
        return null;
    }

    /**
     * Validate discount code
     */
    public boolean validateDiscountCode(String code) {
        Optional<DiscountCode> discountCodeOpt = discountCodeRepository.findByCode(code);
        return discountCodeOpt.isPresent() && !discountCodeOpt.get().getIsUsed();
    }

    /**
     * Delete discount code
     */
    public boolean deleteDiscountCode(Long discountId) {
        if (discountCodeRepository.existsById(discountId)) {
            discountCodeRepository.deleteById(discountId);
            return true;
        }
        return false;
    }

    /**
     * Get flight count for client in a specific year
     */
    public long getFlightCountForYear(Long clientId, Integer year) {
        return milesRewardRepository.findByClientClientId(clientId)
                .stream()
                .filter(reward -> reward.getDate().getYear() == year)
                .count();
    }
}