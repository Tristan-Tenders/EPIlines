package com.timeoutairline.epiline.dtos;

public class Flight {
    private Long flightNum;
    private String depCity;
    private String arrCity;
    private String depHour;
    private String arrHour;
    private Long depAirportId;
    private Long arrAirportId;
    private Airport depAirport;
    private Airport arrAirport;
    private Long planeId;
    private Plane plane;
    private String seatNum;
    private Float fcSeatPrice;
    private Float prSeatPrice;
    private Float bsSeatPrice;
    private Float ecSeatPrice;


    public Long getFlightNum(){
        return flightNum;
    }
    
    public String getDepCity(){
        return depCity;
    }
    
    public String getArrCity(){
        return arrCity;
    }
    
    public String getDepHour(){
        return depHour;
    }
    
    public String getArrHour(){
        return arrHour;
    }
    
    public Long getDepAirportId(){
        return depAirportId;
    }
    
    public Long getArrAirportId(){
        return arrAirportId;
    }
    
    public Airport getDepAirport(){
        return depAirport;
    }
    
    public Airport getArrAirport(){
        return arrAirport;
    }
    
    public Long getPlaneId(){
        return planeId;
    }
    
    public Plane getPlane(){
        return plane;
    }
    
    public String getSeatNum(){
        return seatNum;
    }
    
    public Float getFcSeatPrice(){
        return fcSeatPrice;
    }
    
    public Float getPrSeatPrice(){
        return prSeatPrice;
    }
    
    public Float getBsSeatPrice(){
        return bsSeatPrice;
    }
    
    public Float getEcSeatPrice(){
        return ecSeatPrice;
    }

    public void setFlightNum(Long flightNum){
        this.flightNum = flightNum;
    }
    
    public void setDepCity(String depCity){
        this.depCity = depCity;
    }
    
    public void setArrCity(String arrCity){
        this.arrCity = arrCity;
    }
    
    public void setDepHour(String depHour){
        this.depHour = depHour;
    }
    
    public void setArrHour(String arrHour){
        this.arrHour = arrHour;
    }
    
    public void setDepAirportId(Long depAirportId){
        this.depAirportId = depAirportId;
    }
    
    public void setArrAirportId(Long arrAirportId){
        this.arrAirportId = arrAirportId;
    }
    
    public void setDepAirport(Airport depAirport){
        this.depAirport = depAirport;
    }
    
    public void setArrAirport(Airport arrAirport){
        this.arrAirport = arrAirport;
    }
    
    public void setPlaneId(Long planeId){
        this.planeId = planeId;
    }
    
    public void setPlane(Plane plane){
        this.plane = plane;
    }
    
    public void setSeatNum(String seatNum){
        this.seatNum = seatNum;
    }
    
    public void setFcSeatPrice(Float fcSeatPrice){
        this.fcSeatPrice = fcSeatPrice;
    }
    
    public void setPrSeatPrice(Float prSeatPrice){
        this.prSeatPrice = prSeatPrice;
    }
    
    public void setBsSeatPrice(Float bsSeatPrice){
        this.bsSeatPrice = bsSeatPrice;
    }
    
    public void setEcSeatPrice(Float ecSeatPrice){
        this.ecSeatPrice = ecSeatPrice;
    }
}