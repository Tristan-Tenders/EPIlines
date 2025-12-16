package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Plane;
import com.timeoutairline.epiline.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlaneService {

    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    /**
     * Get all planes
     */
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    /**
     * Get plane by ID
     */
    public Optional<Plane> getPlaneById(Long planeId) {
        return planeRepository.findById(planeId);
    }

    /**
     * Get planes by brand
     */
    public List<Plane> getPlanesByBrand(String brand) {
        return planeRepository.findByBrand(brand);
    }

    /**
     * Get planes by model
     */
    public List<Plane> getPlanesByModel(String model) {
        return planeRepository.findByModel(model);
    }

    /**
     * Get planes by manufacturing year
     */
    public List<Plane> getPlanesByManYear(Integer manYear) {
        return planeRepository.findByManYear(manYear);
    }

    /**
     * Save a new plane
     */
    public Plane savePlane(Plane plane) {
        return planeRepository.save(plane);
    }

    /**
     * Update existing plane
     * Returns null if plane doesn't exist
     */
    public Plane updatePlane(Long planeId, Plane updatedPlane) {
        if (planeRepository.existsById(planeId)) {
            updatedPlane.setPlaneId(planeId);
            return planeRepository.save(updatedPlane);
        }
        return null;
    }

    /**
     * Delete plane by ID
     * Returns true if deleted, false if not found
     */
    public boolean deletePlane(Long planeId) {
        if (planeRepository.existsById(planeId)) {
            planeRepository.deleteById(planeId);
            return true;
        }
        return false;
    }

    /**
     * Check if plane with specific brand and model exists
     */
    public boolean planeExists(String brand, String model) {
        return planeRepository.existsByBrandAndModel(brand, model);
    }
}