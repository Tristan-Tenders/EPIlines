package com.timeoutairline.epiline.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Client Entity - Represents clients table in database
 * Manual getters/setters (matching your Airport entity style)
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(unique = true, nullable = false)
    private Long numPassport;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    //@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<MilesReward> milesRewards = new ArrayList<>();

    // Constructors
    public Client() {
    }

    public Client(Long clientId, Long numPassport, User user) {
        this.clientId = clientId;
        this.numPassport = numPassport;
        this.user = user;
    }

    // Getters
    public Long getClientId() {
        return clientId;
    }

    public Long getNumPassport() {
        return numPassport;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    //public List<MilesReward> getMilesRewards() {
    //    return milesRewards;
    //}

    // Setters
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setNumPassport(Long numPassport) {
        this.numPassport = numPassport;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    //public void setMilesRewards(List<MilesReward> milesRewards) {
    //    this.milesRewards = milesRewards;
    //}

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", numPassport=" + numPassport +
                ", user=" + user +
                ", books=" + books.size() +
                //", milesRewards=" + milesRewards.size() +
                '}';
    }
}