package com.joyonta.springsecuritywithdatabase.controller;

import com.joyonta.springsecuritywithdatabase.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String root() {
        return "Welcome to Spring Security.";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "Welcome to Home.";
    }

    @PutMapping(value = "/admin/{num}")
    public String admin(@PathVariable("num") Integer num, @RequestBody User user) {
        return "Welcome Admin." + num + " put: " + user;
    }

    @PostMapping(value = "/test")
    public String test() {
        return "Test is passed.";
    }

    @DeleteMapping("/deleteDepartmentById/{id}")
    public String deleteDepartmentById (@PathVariable("id") Integer id) {
        return "Department deleted successfully " + id;
    }
}
