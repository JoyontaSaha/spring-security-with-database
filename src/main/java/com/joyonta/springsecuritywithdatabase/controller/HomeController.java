package com.joyonta.springsecuritywithdatabase.controller;

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

    @PostMapping(value = "/admin")
    public String admin(@Param("num") Integer num) {
        System.out.println("admin() called");
        System.out.println("num: " + num);
        return "Welcome Admin." + num;
    }

    @PostMapping(value = "/test")
    public String test() {
        return "Test is passed.";
    }

    @DeleteMapping("/deleteDepartmentById/{id}")
    public String deleteDepartmentById (@Param("id") Integer id) {
        return "Department deleted successfully " + id;
    }
}
