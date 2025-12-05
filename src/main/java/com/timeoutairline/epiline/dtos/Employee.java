package com.timeoutairline.epiline.dtos;

public class Employee {

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
