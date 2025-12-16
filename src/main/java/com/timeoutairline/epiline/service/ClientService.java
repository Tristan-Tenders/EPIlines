package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ClientService - Business logic layer for Client operations
 * Matches the style of AirportService in your project
 */
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Get all clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Get client by ID
     */
    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    /**
     * Get client by passport number
     */
    public Optional<Client> getClientByPassport(Long numPassport) {
        return clientRepository.findByNumPassport(numPassport);
    }

    /**
     * Get client by user ID
     */
    public Optional<Client> getClientByUserId(Long userId) {
        return clientRepository.findByUserId(userId);
    }

    /**
     * Search clients by user first name
     */
    public List<Client> searchClientsByFirstName(String firstName) {
        return clientRepository.findByUserFirstnameContainingIgnoreCase(firstName);
    }

    /**
     * Search clients by user last name
     */
    public List<Client> searchClientsByLastName(String lastName) {
        return clientRepository.findByUserLastnameContainingIgnoreCase(lastName);
    }

    /**
     * Search clients by user email
     */
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByUserEmail(email);
    }

    /**
     * Save a new client
     */
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Update existing client
     * Returns null if client doesn't exist
     */
    public Client updateClient(Long clientId, Client updatedClient) {
        if (clientRepository.existsById(clientId)) {
            updatedClient.setClientId(clientId);
            return clientRepository.save(updatedClient);
        }
        return null;
    }

    /**
     * Delete client by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteClient(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return true;
        }
        return false;
    }

    /**
     * Check if passport number exists
     */
    public boolean passportExists(Long numPassport) {
        return clientRepository.existsByNumPassport(numPassport);
    }

    /**
     * Check if client exists by user ID
     */
    public boolean clientExistsByUserId(Long userId) {
        return clientRepository.existsByUserId(userId);
    }
}