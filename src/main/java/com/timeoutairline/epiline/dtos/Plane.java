package com.timeoutairline.epiline.dtos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plane")

public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long planeId;
    private String brand;
    private String model;
    private Integer manYear;
    private List<Flight> flights;

    public Long getPlaneId(){
        return planeId;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public Integer getManYear(){
        return manYear;
    }

    public List<Flight> getFlights(){
        return flights;
    }

    public void setPlaneId(Long planeId){
        this.planeId = planeId;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setManYear(Integer manYear){
        this.manYear = manYear;
    }

    public void setFlights(List<Flight> flights){
        this.flights = flights;
    }

}
