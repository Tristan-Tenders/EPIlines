package com.timeoutairline.epiline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Book Entity - Represents reservations table in database
 * Manual getters/setters (matching your Airport entity style)
 */
@Entity
@Table(name = "reservations")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    
    @ManyToOne
    @JoinColumn(name = "flight_num", nullable = false)
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