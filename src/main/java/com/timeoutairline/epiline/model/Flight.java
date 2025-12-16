package com.timeoutairline.epiline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightNum;

    @Column(nullable = false)
    private String depCity;

    @Column(nullable = false)
    private String arrCity;

    @Column(nullable = false)
    private LocalDateTime depTime;

    @Column(nullable = false)
    private LocalDateTime arrTime;

    // Foreign key to departure airport
    @Column(name = "dep_airport_id", nullable = false)
    private Long depAirportId;

    // Foreign key to arrival airport
    @Column(name = "arr_airport_id", nullable = false)
    private Long arrAirportId;

    // Foreign key to plane
    @Column(name = "plane_id", nullable = false)
    private Long planeId;

    // Total number of seats available
    @Column(nullable = false)
    private Integer totalSeats;

    // Available seats (decreases when booked)
    @Column(nullable = false)
    private Integer availableSeats;

    // Seat prices by class
    @Column(name = "first_class_price")
    private Float fcSeatPrice;

    @Column(name = "premium_price")
    private Float prSeatPrice;

    @Column(name = "business_price")
    private Float bsSeatPrice;

    @Column(name = "economy_price")
    private Float ecSeatPrice;

    // Flight status
    @Column(nullable = false)
    private String status; // SCHEDULED, BOARDING, DEPARTED, ARRIVED, CANCELLED, DELAYED

    // Constructors
    public Flight() {
    }

    public Flight(Long flightNum, String depCity, String arrCity, LocalDateTime depTime,
                  LocalDateTime arrTime, Long depAirportId, Long arrAirportId, Long planeId,
                  Integer totalSeats, Integer availableSeats, Float fcSeatPrice,
                  Float prSeatPrice, Float bsSeatPrice, Float ecSeatPrice, String status) {
        this.flightNum = flightNum;
        this.depCity = depCity;
        this.arrCity = arrCity;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.depAirportId = depAirportId;
        this.arrAirportId = arrAirportId;
        this.planeId = planeId;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fcSeatPrice = fcSeatPrice;
        this.prSeatPrice = prSeatPrice;
        this.bsSeatPrice = bsSeatPrice;
        this.ecSeatPrice = ecSeatPrice;
        this.status = status;
    }

    // Getters
    public Long getFlightNum() {
        return flightNum;
    }

    public String getDepCity() {
        return depCity;
    }

    public String getArrCity() {
        return arrCity;
    }

    public LocalDateTime getDepTime() {
        return depTime;
    }

    public LocalDateTime getArrTime() {
        return arrTime;
    }

    public Long getDepAirportId() {
        return depAirportId;
    }

    public Long getArrAirportId() {
        return arrAirportId;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public Float getFcSeatPrice() {
        return fcSeatPrice;
    }

    public Float getPrSeatPrice() {
        return prSeatPrice;
    }

    public Float getBsSeatPrice() {
        return bsSeatPrice;
    }

    public Float getEcSeatPrice() {
        return ecSeatPrice;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setFlightNum(Long flightNum) {
        this.flightNum = flightNum;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }

    public void setDepTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }

    public void setArrTime(LocalDateTime arrTime) {
        this.arrTime = arrTime;
    }

    public void setDepAirportId(Long depAirportId) {
        this.depAirportId = depAirportId;
    }

    public void setArrAirportId(Long arrAirportId) {
        this.arrAirportId = arrAirportId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setFcSeatPrice(Float fcSeatPrice) {
        this.fcSeatPrice = fcSeatPrice;
    }

    public void setPrSeatPrice(Float prSeatPrice) {
        this.prSeatPrice = prSeatPrice;
    }

    public void setBsSeatPrice(Float bsSeatPrice) {
        this.bsSeatPrice = bsSeatPrice;
    }

    public void setEcSeatPrice(Float ecSeatPrice) {
        this.ecSeatPrice = ecSeatPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNum=" + flightNum +
                ", depCity='" + depCity + '\'' +
                ", arrCity='" + arrCity + '\'' +
                ", depTime=" + depTime +
                ", arrTime=" + arrTime +
                ", depAirportId=" + depAirportId +
                ", arrAirportId=" + arrAirportId +
                ", planeId=" + planeId +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", status='" + status + '\'' +
                '}';
    }
}