package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * Login endpoint for user authentication.
	 * Expects username and password in the request body.
	 */
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User loginRequest) {
		String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
		return ResponseEntity.ok(token);
	}

	/**
	 * Signup endpoint for user registration.
	 * Expects username, password, and roles in the request body.
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User signupRequest) {
		String response = authService.signup(signupRequest);
		return ResponseEntity.ok(response);
	}
}
