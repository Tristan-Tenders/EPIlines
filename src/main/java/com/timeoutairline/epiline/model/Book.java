package com.timeoutairline.epiline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    
    @ManyToOne
    @JoinColumn(name = "flight_num", nullable = false)
    @JsonIgnore
    private Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;
    
    @Column(nullable = false)
    private String typeSeat;
    
    // Constructors
    public Book() {
    }
    
    public Book(Long reservationId, Flight flight, Client client, String typeSeat) {
        this.reservationId = reservationId;
        this.flight = flight;
        this.client = client;
        this.typeSeat = typeSeat;
    }
    
    // Getters
    public Long getReservationId(){
        return reservationId;
    }
    
    public Flight getFlight(){
        return flight;
    }
    
    public Client getClient(){
        return client;
    }
    
    public String getTypeSeat(){
        return typeSeat;
    }
    
    // Setters
    public void setReservationId(Long reservationId){
        this.reservationId = reservationId;
    }
    
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
    
    public void setTypeSeat(String typeSeat){
        this.typeSeat = typeSeat;
    }
    
    @JsonProperty("flightNum")
    public Long getFlightNum() {
        return flight != null ? flight.getFlightNum() : null;
    }
    
    @JsonProperty("clientId")
    public Long getClientId() {
        return client != null ? client.getClientId() : null;
    }

    @JsonProperty("flightNum")
    public void setFlightNum(Long flightNum) {
        if (this.flight == null) {
            this.flight = new Flight();
        }
        this.flight.setFlightNum(flightNum);
    }

    @JsonProperty("clientId")
    public void setClientId(Long clientId) {
        if (this.client == null) {
            this.client = new Client();
        }
        this.client.setClientId(clientId);
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "reservationId=" + reservationId +
                ", flight=" + flight +
                ", client=" + client +
                ", typeSeat='" + typeSeat + '\'' +
                '}';
    }
}