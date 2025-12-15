package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Plane;
import com.timeoutairline.epiline.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * PlaneController - REST API endpoints for Plane management
 * Matches the style of UserController in your project
 */
@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "*")
public class PlaneController {

    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    /**
     * GET /api/planes - Get all planes
     */
    @GetMapping
    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planes = planeService.getAllPlanes();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    /**
     * GET /api/planes/{id} - Get plane by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable Long id) {
        Optional<Plane> plane = planeService.getPlaneById(id);
        return plane.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/planes/brand/{brand} - Get planes by brand
     */
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Plane>> getPlanesByBrand(@PathVariable String brand) {
        List<Plane> planes = planeService.getPlanesByBrand(brand);
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    /**
     * GET /api/planes/model/{model} - Get planes by model
     */
    @GetMapping("/model/{model}")
    public ResponseEntity<List<Plane>> getPlanesByModel(@PathVariable String model) {
        List<Plane> planes = planeService.getPlanesByModel(model);
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    /**
     * GET /api/planes/year/{year} - Get planes by manufacturing year
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Plane>> getPlanesByYear(@PathVariable Integer year) {
        List<Plane> planes = planeService.getPlanesByManYear(year);
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    /**
     * POST /api/planes - Create new plane
     */
    @PostMapping
    public ResponseEntity<?> createPlane(@RequestBody Plane plane) {
        // Check if plane with same brand and model already exists
        if (planeService.planeExists(plane.getBrand(), plane.getModel())) {
            return new ResponseEntity<>("Plane with this brand and model already exists", HttpStatus.CONFLICT);
        }
        Plane createdPlane = planeService.savePlane(plane);
        return new ResponseEntity<>(createdPlane, HttpStatus.CREATED);
    }

    /**
     * PUT /api/planes/{id} - Update existing plane
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlane(@PathVariable Long id, @RequestBody Plane updatedPlane) {
        // Check if plane exists
        Optional<Plane> existingPlane = planeService.getPlaneById(id);
        if (existingPlane.isEmpty()) {
            return new ResponseEntity<>("Plane not found", HttpStatus.NOT_FOUND);
        }

        // Check if updating to a brand/model that already exists (for a different plane)
        Optional<Plane> duplicatePlane = planeService.getPlanesByBrand(updatedPlane.getBrand())
                .stream()
                .filter(p -> p.getModel().equals(updatedPlane.getModel()) && !p.getPlaneId().equals(id))
                .findFirst();

        if (duplicatePlane.isPresent()) {
            return new ResponseEntity<>("Another plane with this brand and model already exists", HttpStatus.CONFLICT);
        }

        Plane plane = planeService.updatePlane(id, updatedPlane);
        return new ResponseEntity<>(plane, HttpStatus.OK);
    }

    /**
     * DELETE /api/planes/{id} - Delete plane
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlane(@PathVariable Long id) {
        if (planeService.deletePlane(id)) {
            return new ResponseEntity<>("Plane deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Plane not found", HttpStatus.NOT_FOUND);
        }
    }
}