package com.timeoutairline.epiline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(unique = true, nullable = false, length = 3)
    private String code;

    // Constructors
    public Airport() {
    }

    public Airport(Long airportId, String name, String country, String city, String code) {
        this.airportId = airportId;
        this.name = name;
        this.country = country;
        this.city = city;
        this.code = code;
    }

    // Getters
    public Long getAirportId() {
        return airportId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    // Setters
    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}