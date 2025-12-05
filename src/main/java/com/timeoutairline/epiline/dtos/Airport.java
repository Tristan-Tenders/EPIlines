package com.timeoutairline.epiline.dtos;

public class Airport {

    private Long airportId;
    private String name;
    private String country;
    private String city;

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

}
