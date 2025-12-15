package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserService - Business logic layer for User operations
 * Contains all the business rules and orchestrates data access
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users from database
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by ID
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Get user by email
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Save a new user or update existing
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update existing user
     * Returns null if user doesn't exist
     */
    public User updateUser(Long id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);  // Lombok @Data provides setId()
            return userRepository.save(updatedUser);
        }
        return null;
    }

    /**
     * Delete user by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Check if email already exists
     */
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
