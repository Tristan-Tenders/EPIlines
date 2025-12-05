package com.timeoutairline.epiline.dtos;
import java.util.ArrayList;
import java.util.List;

public class Airport {
    private Long airportId;
    private String name;
    private String country;
    private String city;
    private List<Flight> flights = new ArrayList<>();
    
    public Long getAirId(){
        return airportId;
    }

    public String getAirName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

    public String getCity(){
        return city;
    }

    public List<Flight> getFlights(){
        return flights;
    }

    public void setAirId(Long airportId){
        this.airportId = airportId;
    }

    public void setAirName(String name){
        this.name = name;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setCity(String city){
        this.city = city;
    }
    
    public void setFlights(List<Flight> flights){
        this.flights = flights;
    }
}