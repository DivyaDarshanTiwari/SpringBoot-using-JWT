package com.example.springoot_using_jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData(Authentication auth) {
        return ResponseEntity.ok("Hello, admin  " + auth.getName() + "! You accessed protected data.");
    }
}
