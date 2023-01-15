package com.example.calendar.controllers;

import com.example.calendar.models.User;
import com.example.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class AdminController {
    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "userList")
    public List<User> userlist(){
        return userRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "userListDelete/{id}")
    public void usersDelete(@PathVariable("id") Long id){
       userRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "userListChange/{id}")
    public void userChange(@PathVariable("id") Long id, @RequestParam String name, @RequestParam String email){
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setUsername(name);
        userToUpdate.setEmail(email);
        userRepository.save(userToUpdate);
    }
}
