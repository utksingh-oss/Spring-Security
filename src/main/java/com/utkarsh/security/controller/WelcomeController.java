package com.utkarsh.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/hello-world")
    public String welcome() {
        return "Welcome World!!";
    }
}
