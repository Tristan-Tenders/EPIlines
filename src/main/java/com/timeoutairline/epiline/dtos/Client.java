package com.timeoutairline.epiline.dtos;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private Long numPassport;
    private Users user;
    private List<Book> books = new ArrayList<>();

    public Long getNumPassport(){

        return numPassport;
    }

    public List<Book> getBooks(){
        return books;
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

    public void setBooks(List<Book> books){
        this.books = books;
    }

}
