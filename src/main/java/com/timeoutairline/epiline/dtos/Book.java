package com.timeoutairline.epiline.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long reservationId;
    private Flight flight;
    private Long flightId;
    private Client client;
    private Long clientId;
    private String typeSeat;

    
    public Long getReservationId(){
        return reservationId;
    }
    
    public Flight getFlight(){
        return flight;
    }
    
    public Long getFlightId(){
        return flightId;
    }
    
    public Client getClient(){
        return client;
    }
    
    public Long getClientId(){
        return clientId;
    }
    
    public String getTypeSeat(){
        return typeSeat;
    }


    public void setReservationId(Long reservationId){
        this.reservationId = reservationId;
    }
    
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    
    public void setFlightId(Long flightId){
        this.flightId = flightId;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
    
    public void setClientId(Long clientId){
        this.clientId = clientId;
    }
    
    public void setTypeSeat(String typeSeat){
        this.typeSeat = typeSeat;
    }
}