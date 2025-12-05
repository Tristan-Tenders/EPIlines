package com.timeoutairline.epiline.dtos;

public class Employee {

    private Integer empNum;
    private String profession;
    private String title;

    public Integer getEmpNum(){
        return empNum;
    }

    public String getProfession(){
        return profession;
    }

    public String getTitle(){
        return title;
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

}
