package com.example.demo.controllers;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
public class RController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/roles")
    ResponseEntity<Role> createUser(@RequestBody Role request) {
        Role _role = roleRepository.save(request);
        return new ResponseEntity<>(_role, HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") String id) {
        Optional<Role> roleData = roleRepository.findById(id);

        if (roleData.isPresent()) {
            return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateUser(@PathVariable("id") String id, @RequestBody Role role) {
        Optional<Role> roleData = roleRepository.findById(id);

        if (roleData.isPresent()) {
            Role _role = roleData.get();
            _role.setName(role.getName());
            return new ResponseEntity<>(roleRepository.save(_role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable("id") String id) {
        try {
            roleRepository.deleteById(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/roles")
    public ResponseEntity<String> deleteAllRoles() {
        try {
            roleRepository.deleteAll();
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}