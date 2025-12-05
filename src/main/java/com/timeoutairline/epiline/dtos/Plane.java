package com.timeoutairline.epiline.dtos;


public class Plane {

    private Long planeId;
    private String brand;
    private String model;
    private Integer manYear;

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

}
