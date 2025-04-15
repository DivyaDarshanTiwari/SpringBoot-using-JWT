package com.example.springoot_using_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/onlyUser")
public class UserController {

    @GetMapping("/hi")
    public String getMethodName() {
        return new String("hi only user");
    }

}
