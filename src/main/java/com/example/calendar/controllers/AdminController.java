package com.example.calendar.controllers;

import com.example.calendar.models.User;
import com.example.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class AdminController {
    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "userList")
    public List<User> userlist(){
        return userRepository.findAll();
    }

}
