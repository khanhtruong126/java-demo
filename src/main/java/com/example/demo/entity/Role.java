package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

//@Entity(name = "role")
public class Role {
    @Id
    private String id;
//    private RoleKey key;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_role", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key column for User
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key column for Course
    )
    private List<User> users;
}
