package com.timeoutairline.epiline.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MilesReward Entity - Represents miles_rewards table in database
 * Manual getters/setters (matching your Client entity style)
 */
@Entity
@Table(name = "miles_rewards")
public class MilesReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name = "flight_num", nullable = false)
    @JsonIgnore
    private Flight flight;

    @Column(nullable = false)
    private LocalDate date;

    // Constructors
    public MilesReward() {
    }

    public MilesReward(Long rewardId, Client client, Flight flight, LocalDate date) {
        this.rewardId = rewardId;
        this.client = client;
        this.flight = flight;
        this.date = date;
    }

    // Getters
    public Long getRewardId() {
        return rewardId;
    }

    public Client getClient() {
        return client;
    }

    public Flight getFlight() {
        return flight;
    }

    public LocalDate getDate() {
        return date;
    }

    @JsonProperty("flightNum")
    public Long getFlightNum() {
        return flight != null ? flight.getFlightNum() : null;
    }

    @JsonProperty("clientId")
    public Long getClientId() {
        return client != null ? client.getClientId() : null;
    }

    // Setters
    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MilesReward{" +
                "rewardId=" + rewardId +
                ", client=" + client.getClientId() +
                ", flight=" + flight.getFlightNum() +
                ", date=" + date +
                '}';
    }
}