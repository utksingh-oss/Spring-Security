package com.utkarsh.security.controller;

import com.utkarsh.security.entity.User;
import com.utkarsh.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userRequest) {
        return userService.login(userRequest.getUsername(), userRequest.getPassword());
    }
}
