package com.example.demo.controllers;

import com.example.demo.dto.request.RoleUserCreationRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/assign-roles-to-users")
    ResponseEntity<User> asignRolesToUsers(@RequestBody RoleUserCreationRequest request) {
        Optional<User> userData = userRepository.findById(request.userId);
        List<Role> rolesData = roleRepository.findByRoleByIds(request.roleIds);

        if (userData.isPresent()) {
            User _user = userData.get();
            rolesData.forEach(role -> {
                role.setUsers(List.of(_user));
            });
            _user.setRoles(rolesData);
            userRepository.save(_user);
            return new ResponseEntity<>(_user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User request) {
        User _userTemp = new User();
        String id = UUID.randomUUID().toString().replace("-", "");
        _userTemp.setId(id);
        _userTemp.setName(request.getName());
        _userTemp.setAge(request.getAge());
        _userTemp.setUsername(request.getUsername());
        _userTemp.setPassword(request.getPassword());


        request.getUserAddresses().forEach(userAddress -> {
            userAddress.setUser(_userTemp);
        });
        _userTemp.setUserAddresses(request.getUserAddresses());

        User _user = userRepository.save(_userTemp);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setAge(user.getAge());
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
