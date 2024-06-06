package com.example.demo.controllers;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class RController {
    @GetMapping("/hello")
    public String getHello() {
        return "Hello world";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
//        User user1 = new User(1, "truong", 26);

//        List<User> users = new ArrayList<User>();
//        users.add(user1);

//        return users;
        return new ArrayList<>();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User newUser) {
        return newUser;
    }
}