package com.joyonta.springsecuritywithdatabase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String root() {
        return "Welcome to Spring Security";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "Welcome to Home";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "Welcome Admin";
    }
}
