package com.timeoutairline.epiline.dtos;


public class MilesReward {
    
    private Client client;
    private Long clientId;
    private Flight flight;
    private Long flightId;
    private String date;
    
    
    public Client getClient(){
        return client;
    }
    
    public Long getClientId(){
        return clientId;
    }
    
    public Flight getFlight(){
        return flight;
    }
    
    public Long getFlightId(){
        return flightId;
    }
    
    public String getDate(){
        return date;
    }
    
    
    public void setClient(Client client){
        this.client = client;
    }
    
    public void setClientId(Long clientId){
        this.clientId = clientId;
    }
    
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    
    public void setFlightId(Long flightId){
        this.flightId = flightId;
    }
    
    public void setDate(String date){
        this.date = date;
    }
}