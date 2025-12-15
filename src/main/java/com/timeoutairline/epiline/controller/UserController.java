package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * UserController - REST API endpoints for User management
 * Handles HTTP requests and returns appropriate responses
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")  // Allow all origins for development
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/users - Get all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * GET /api/users/{id} - Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/users/email/{email} - Get user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST /api/users - Create new user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Check if email already exists (Lombok @Data provides getEmail())
        if (userService.emailExists(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * PUT /api/users/{id} - Update existing user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Check if user exists
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Check if email is being changed and if new email already exists
        if (!existingUser.get().getEmail().equals(updatedUser.getEmail())
                && userService.emailExists(updatedUser.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }

        User user = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * DELETE /api/users/{id} - Delete user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
