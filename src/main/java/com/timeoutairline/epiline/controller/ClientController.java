package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ClientController - REST API endpoints for Client management
 * Matches the style of AirportController in your project
 */
@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * GET /api/clients - Get all clients
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * GET /api/clients/{id} - Get client by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/clients/passport/{passport} - Get client by passport number
     */
    @GetMapping("/passport/{passport}")
    public ResponseEntity<Client> getClientByPassport(@PathVariable Long passport) {
        Optional<Client> client = clientService.getClientByPassport(passport);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/clients/user/{userId} - Get client by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Client> getClientByUserId(@PathVariable Long userId) {
        Optional<Client> client = clientService.getClientByUserId(userId);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/clients/search/firstName?name={firstName} - Search clients by first name
     */
    @GetMapping("/search/firstName")
    public ResponseEntity<List<Client>> searchClientsByFirstName(@RequestParam String name) {
        List<Client> clients = clientService.searchClientsByFirstName(name);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * GET /api/clients/search/lastName?name={lastName} - Search clients by last name
     */
    @GetMapping("/search/lastName")
    public ResponseEntity<List<Client>> searchClientsByLastName(@RequestParam String name) {
        List<Client> clients = clientService.searchClientsByLastName(name);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * GET /api/clients/search/email?email={email} - Search client by email
     */
    @GetMapping("/search/email")
    public ResponseEntity<Client> getClientByEmail(@RequestParam String email) {
        Optional<Client> client = clientService.getClientByEmail(email);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST /api/clients - Create new client
     */
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        // Validate passport number
        if (client.getNumPassport() == null) {
            return new ResponseEntity<>("Passport number is required", HttpStatus.BAD_REQUEST);
        }

        // Check if passport already exists
        if (clientService.passportExists(client.getNumPassport())) {
            return new ResponseEntity<>("Client with this passport number already exists", HttpStatus.CONFLICT);
        }

        // Validate user
        if (client.getUser() == null) {
            return new ResponseEntity<>("User is required", HttpStatus.BAD_REQUEST);
        }

        // Check if user already has a client profile
        if (clientService.clientExistsByUserId(client.getUser().getId())) {
            return new ResponseEntity<>("This user already has a client profile", HttpStatus.CONFLICT);
        }

        Client createdClient = clientService.saveClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    /**
     * PUT /api/clients/{id} - Update existing client
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        // Check if client exists
        Optional<Client> existingClient = clientService.getClientById(id);
        if (existingClient.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        // Validate passport number
        if (updatedClient.getNumPassport() == null) {
            return new ResponseEntity<>("Passport number is required", HttpStatus.BAD_REQUEST);
        }

        // Check if updating to a passport that already exists (for a different client)
        Optional<Client> duplicateClient = clientService.getClientByPassport(updatedClient.getNumPassport());
        if (duplicateClient.isPresent() && !duplicateClient.get().getClientId().equals(id)) {
            return new ResponseEntity<>("Another client with this passport number already exists", HttpStatus.CONFLICT);
        }

        // Validate user
        if (updatedClient.getUser() == null) {
            return new ResponseEntity<>("User is required", HttpStatus.BAD_REQUEST);
        }

        Client client = clientService.updateClient(id, updatedClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * DELETE /api/clients/{id} - Delete client
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }
}