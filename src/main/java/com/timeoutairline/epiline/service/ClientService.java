package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Client;
import com.timeoutairline.epiline.model.User;
import com.timeoutairline.epiline.repository.ClientRepository;
import com.timeoutairline.epiline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Optional<Client> getClientByPassport(Long numPassport) {
        return clientRepository.findByNumPassport(numPassport);
    }

    public Optional<Client> getClientByUserId(Long userId) {
        return clientRepository.findByUser_Id(userId);
    }

    public List<Client> searchClientsByFirstName(String firstName) {
        return clientRepository.findByUserFirstnameContainingIgnoreCase(firstName);
    }

    public List<Client> searchClientsByLastName(String lastName) {
        return clientRepository.findByUserLastnameContainingIgnoreCase(lastName);
    }

    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByUserEmail(email);
    }

    public Client saveClient(Client client) {
        // This is the only line that fixes everything
        User realUser = userRepository.findById(client.getUserId())
                .orElseThrow(() -> new RuntimeException("This userId does not exist! Use real userId from your users table"));

        client.setUser(realUser);   // now it uses real user – no foreign key error
        return clientRepository.save(client);
    }

    public Client updateClient(Long clientId, Client updatedClient) {
        if (clientRepository.existsById(clientId)) {
            updatedClient.setClientId(clientId);
            return clientRepository.save(updatedClient);
        }
        return null;
    }

    public boolean deleteClient(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return true;
        }
        return false;
    }

    public boolean passportExists(Long numPassport) {
        return clientRepository.existsByNumPassport(numPassport);
    }

    public boolean clientExistsByUserId(Long userId) {
        return clientRepository.existsByUser_Id(userId);
    }
}