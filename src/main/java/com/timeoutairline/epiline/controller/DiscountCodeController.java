package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.DiscountCode;
import com.timeoutairline.epiline.service.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    /**
     * GET /api/discounts - Get all discount codes
     */
    @GetMapping
    public ResponseEntity<List<DiscountCode>> getAllDiscountCodes() {
        List<DiscountCode> discountCodes = discountCodeService.getAllDiscountCodes();
        return new ResponseEntity<>(discountCodes, HttpStatus.OK);
    }

    /**
     * GET /api/discounts/{id} - Get discount code by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscountCode> getDiscountCodeById(@PathVariable Long id) {
        Optional<DiscountCode> discountCode = discountCodeService.getDiscountCodeById(id);
        return discountCode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/discounts/client/{clientId} - Get discount codes by client ID
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DiscountCode>> getDiscountCodesByClientId(@PathVariable Long clientId) {
        List<DiscountCode> discountCodes = discountCodeService.getDiscountCodesByClientId(clientId);
        return new ResponseEntity<>(discountCodes, HttpStatus.OK);
    }

    /**
     * GET /api/discounts/client/{clientId}/unused - Get unused discount codes by client
     */
    @GetMapping("/client/{clientId}/unused")
    public ResponseEntity<List<DiscountCode>> getUnusedDiscountCodesByClient(@PathVariable Long clientId) {
        List<DiscountCode> discountCodes = discountCodeService.getUnusedDiscountCodesByClient(clientId);
        return new ResponseEntity<>(discountCodes, HttpStatus.OK);
    }

    /**
     * GET /api/discounts/code/{code} - Get discount code by code string
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<DiscountCode> getDiscountCodeByCode(@PathVariable String code) {
        Optional<DiscountCode> discountCode = discountCodeService.getDiscountCodeByCode(code);
        return discountCode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/discounts/year/{year} - Get discount codes by year
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiscountCode>> getDiscountCodesByYear(@PathVariable Integer year) {
        List<DiscountCode> discountCodes = discountCodeService.getDiscountCodesByYear(year);
        return new ResponseEntity<>(discountCodes, HttpStatus.OK);
    }

    /**
     * POST /api/discounts/validate - Validate a discount code
     */
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateDiscountCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (code == null || code.isEmpty()) {
            return new ResponseEntity<>(Map.of("valid", false, "message", "Code is required"),
                    HttpStatus.BAD_REQUEST);
        }

        boolean isValid = discountCodeService.validateDiscountCode(code);
        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        if (isValid) {
            Optional<DiscountCode> discountCode = discountCodeService.getDiscountCodeByCode(code);
            discountCode.ifPresent(dc -> {
                response.put("discountPercentage", dc.getDiscountPercentage());
                response.put("year", dc.getYear());
            });
            response.put("message", "Discount code is valid");
        } else {
            response.put("message", "Discount code is invalid or already used");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST /api/discounts/use - Mark discount code as used
     */
    @PostMapping("/use")
    public ResponseEntity<?> useDiscountCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (code == null || code.isEmpty()) {
            return new ResponseEntity<>("Code is required", HttpStatus.BAD_REQUEST);
        }

        DiscountCode usedCode = discountCodeService.useDiscountCode(code);
        if (usedCode != null) {
            return new ResponseEntity<>(usedCode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Discount code not found or already used", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/discounts/client/{clientId}/stats/{year} - Get client flight statistics for a year
     */
    @GetMapping("/client/{clientId}/stats/{year}")
    public ResponseEntity<Map<String, Object>> getClientStats(@PathVariable Long clientId,
                                                              @PathVariable Integer year) {
        long flightCount = discountCodeService.getFlightCountForYear(clientId, year);
        boolean hasDiscount = discountCodeService.getDiscountCodesByClientId(clientId)
                .stream()
                .anyMatch(dc -> dc.getYear().equals(year));

        Map<String, Object> stats = new HashMap<>();
        stats.put("clientId", clientId);
        stats.put("year", year);
        stats.put("flightCount", flightCount);
        stats.put("hasDiscountCode", hasDiscount);
        stats.put("qualifiesForDiscount", flightCount >= 3);
        stats.put("flightsNeeded", Math.max(0, 3 - flightCount));

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    /**
     * DELETE /api/discounts/{id} - Delete discount code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscountCode(@PathVariable Long id) {
        if (discountCodeService.deleteDiscountCode(id)) {
            return new ResponseEntity<>("Discount code deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Discount code not found", HttpStatus.NOT_FOUND);
        }
    }
}