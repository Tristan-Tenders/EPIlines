package com.timeoutairline.epiline.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    @GetMapping("/users")
    public String Users(){
        return "HARROW";
    }
    @PostMapping("/users")
    public String Users(@RequestBody Users user){
        return usersService.saveUser(user);
    }
}
