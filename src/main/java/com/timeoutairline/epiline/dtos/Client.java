package com.timeoutairline.epiline.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Client DTO - Updated to use UserDTO instead of Users
 */
public class Client {

    private Long numPassport;
    private UserDTO user;  // Changed from Users to UserDTO
    private List<Book> books = new ArrayList<>();
    private List<MilesReward> milesRewards = new ArrayList<>();

    public Long getNumPassport(){
        return numPassport;
    }

    public List<Book> getBooks(){
        return books;
    }

    public UserDTO getUser(){  // Changed return type
        return user;
    }

    public List<MilesReward> getMilesRewards(){
        return milesRewards;
    }


    public void setNumPassport(Long numPassport){
        this.numPassport = numPassport;
    }

    public void setUser(UserDTO user){  // Changed parameter type
        this.user = user;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public void setMilesRewards(List<MilesReward> milesRewards){
        this.milesRewards = milesRewards;
    }
}
