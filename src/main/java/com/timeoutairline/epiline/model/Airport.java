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

    // Constructors
    public Airport() {
    }

    public Airport(Long airportId, String name, String country, String city, String code) {
        this.airportId = airportId;
        this.name = name;
        this.country = country;
        this.city = city;
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

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}