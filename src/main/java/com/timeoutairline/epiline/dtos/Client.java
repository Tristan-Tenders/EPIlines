package com.timeoutairline.epiline.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long numPassport;
    private Users user;
    private List<Book> books = new ArrayList<>();
    private List<MilesReward> milesRewards = new ArrayList<>();

    public Long getNumPassport(){
        return numPassport;
    }

    public List<Book> getBooks(){
        return books;
    }

    public Users getUser(){
        return user;
    }

    public List<MilesReward> getMilesRewards(){
        return milesRewards;
    }


    public void setNumPassport(Long numPassport){
        this.numPassport = numPassport;
    }
    
    public void setUser(Users user){
        this.user = user;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public void setMilesRewards(List<MilesReward> milesRewards){
        this.milesRewards = milesRewards;
}

}
