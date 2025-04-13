package com.example.springoot_using_jwt.controller;

import com.example.springoot_using_jwt.model.User;
import com.example.springoot_using_jwt.repository.UserRepository;
import com.example.springoot_using_jwt.service.MyUserDetailsService;
import com.example.springoot_using_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login endpoint to authenticate user and return JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Load user details from the database
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            // Generate JWT
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(jwt); // Return JWT if authentication is successful
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    // Register endpoint to create a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check if the user already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(400).body("Username is already taken");
        }

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user in the repository
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser); // Return the saved user
    }
}
