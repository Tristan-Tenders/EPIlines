package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    // Find discount codes by client ID
    List<DiscountCode> findByClientClientId(Long clientId);

    // Find discount code by code string
    Optional<DiscountCode> findByCode(String code);

    // Find discount codes by year
    List<DiscountCode> findByYear(Integer year);

    // Find discount codes by client and year
    Optional<DiscountCode> findByClientClientIdAndYear(Long clientId, Integer year);

    // Find unused discount codes by client
    List<DiscountCode> findByClientClientIdAndIsUsed(Long clientId, Boolean isUsed);

    // Check if discount code exists for client and year
    boolean existsByClientClientIdAndYear(Long clientId, Integer year);

    // Check if code string exists
    boolean existsByCode(String code);
}