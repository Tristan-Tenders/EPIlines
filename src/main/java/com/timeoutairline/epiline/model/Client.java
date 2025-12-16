package com.timeoutairline.epiline.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


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
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MilesReward> milesRewards = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DiscountCode> discountCodes = new ArrayList<>();

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

    public List<MilesReward> getMilesRewards() {
        return milesRewards;
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

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

    public void setMilesRewards(List<MilesReward> milesRewards) {
        this.milesRewards = milesRewards;
    }

    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }

    // Virtual getters/setters for JSON serialization
    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    @JsonProperty("userId")
    public void setUserId(Long userId) {
        if (userId != null) {
            if (this.user == null) {
                this.user = new User();
            }
            this.user.setId(userId);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", numPassport=" + numPassport +
                ", user=" + user +
                ", books=" + books.size() +
                ", milesRewards=" + milesRewards.size() +
                ", discountCodes=" + discountCodes.size() +
                '}';
    }
}