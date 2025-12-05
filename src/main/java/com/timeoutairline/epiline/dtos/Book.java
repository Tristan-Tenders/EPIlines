package com.timeoutairline.epiline.dtos;

public class Book {
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