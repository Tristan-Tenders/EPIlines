package com.timeoutairline.epiline.dtos;

public class Client {

    private Long numPassport;
    private Users user;

    public Long getNumPassport(){

        return numPassport;
    }

    public void setNumPassport(Long numPassport){

        this.numPassport = numPassport;
    }

    public Users getUser(){
        return user;
    }
    
    public void setUser(Users user){
        this.user = user;
    }

}
