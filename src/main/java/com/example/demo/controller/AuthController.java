package com.example.demo.controller;

import com.example.demo.models.SignupRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final AuthenticationManager authenticationManager;
	    private final JwtUtil jwtUtil;
	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public AuthController(
	            AuthenticationManager authenticationManager,
	            JwtUtil jwtUtil,
	            UserRepository userRepository,
	            PasswordEncoder passwordEncoder) {
	        this.authenticationManager = authenticationManager;
	        this.jwtUtil = jwtUtil;
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    /**
	     * Login endpoint for generating JWT.
	     * @param username User's username.
	     * @param password User's password.
	     * @return JWT token if authentication is successful.
	     */
	    @PostMapping("/login")
	    public String login(@RequestParam String username, @RequestParam String password) {
	        try {
	            // Authenticate the user
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	            // Generate and return JWT
	            return jwtUtil.generateToken(username);
	        } catch (AuthenticationException e) {
	            throw new RuntimeException("Invalid credentials");
	        }
	    }

	    /**
	     * Signup endpoint for registering new users.
	     * @param signupRequest Request body containing username, password, and roles.
	     * @return Success or error message.
	     */
	    @PostMapping("/signup")
	    public String signup(@RequestBody SignupRequest signupRequest) {
	        // Check if username already exists
	        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
	            return "Error: Username is already taken!";
	        }

	        // Create new user
	        User user = new User();
	        user.setUsername(signupRequest.getUsername());
	        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

	        // Assign roles or set default role
	        if (signupRequest.getRoles() == null || signupRequest.getRoles().isEmpty()) {
	            user.setRoles(new HashSet<>(Set.of("USER"))); // Default role
	        } else {
	            user.setRoles(signupRequest.getRoles());
	        }

	        // Save user to the database
	        userRepository.save(user);

	        return "User registered successfully!";
	    }
}