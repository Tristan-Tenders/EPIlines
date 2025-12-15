package com.timeoutairline.epiline.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer empNum;
    private String profession;
    private String title;
    private Users user;

    public Integer getEmpNum(){
        return empNum;
    }

    public String getProfession(){
        return profession;
    }

    public String getTitle(){
        return title;
    }

    public Users getUser(){
        return user;
    }

    public void setEmpNum(Integer empNum){
        this.empNum = empNum;
    }

    public void setProfession(String profession){
        this.profession = profession;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setUser(Users user){
        this.user = user;
    }

}
