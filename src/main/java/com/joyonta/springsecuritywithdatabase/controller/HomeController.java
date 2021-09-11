package com.joyonta.springsecuritywithdatabase.controller;

import com.joyonta.springsecuritywithdatabase.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/adminDelete/{id}")
    public String adminDelete(@PathVariable("id") Integer id) {
        return "adminDelete id "+id+" successful";
    }

    @PostMapping(value = "/userPost")
    public String userPost(@RequestBody User user) {
        return "userPost "+user+" successful";
    }

    @PutMapping(value = "/userPut/{id}")
    public String userPut(@PathVariable(value = "id") Integer id, @RequestBody User user) {
        return "userPut id " + id + " for "+user+" successful";
    }
}

