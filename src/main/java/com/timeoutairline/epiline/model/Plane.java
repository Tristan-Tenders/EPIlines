package com.timeoutairline.epiline.model;

import jakarta.persistence.*;


@Entity
@Table(name = "planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "manufacturing_year")
    private Integer manYear;

    // Constructors
    public Plane() {
    }

    public Plane(Long planeId, String brand, String model, Integer manYear) {
        this.planeId = planeId;
        this.brand = brand;
        this.model = model;
        this.manYear = manYear;
    }

    // Getters
    public Long getPlaneId() {
        return planeId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getManYear() {
        return manYear;
    }

    // Setters
    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManYear(Integer manYear) {
        this.manYear = manYear;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeId=" + planeId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manYear=" + manYear +
                '}';
    }
}