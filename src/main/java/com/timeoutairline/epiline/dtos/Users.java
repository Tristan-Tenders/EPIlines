package com.timeoutairline.epiline.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private Integer phone;
    private String birthdate;
    private Client client;
    private Employee employee;

    //getters
    public Integer getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public Integer getPhone(){
        return phone;
    }
    public String getBirthdate(){
        return birthdate;
    }

    public Client getClient(){
        return client;
    }

    public Employee getEmployee(){
        return employee;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(Integer phone){
        this.phone=phone;
    }
    public void setBirthdate(String birthdate){
        this.birthdate=birthdate;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

}