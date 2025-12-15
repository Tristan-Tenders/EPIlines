package com.timeoutairline.epiline.dtos;

/**
 * Employee DTO - Updated to use UserDTO instead of Users
 */
public class Employee {

    private Integer empNum;
    private String profession;
    private String title;
    private UserDTO user;  // Changed from Users to UserDTO

    public Integer getEmpNum(){
        return empNum;
    }

    public String getProfession(){
        return profession;
    }

    public String getTitle(){
        return title;
    }

    public UserDTO getUser(){  // Changed return type
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

    public void setUser(UserDTO user){  // Changed parameter type
        this.user = user;
    }
}
