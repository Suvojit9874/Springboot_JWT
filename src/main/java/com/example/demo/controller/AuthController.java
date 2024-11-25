package com.example.demo.controller;//package com.example.demo.controller;
//
//import com.example.demo.models.User;
//import com.example.demo.service.AuthService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//	private final AuthService authService;
//
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}
//
//	/**
//	 * Login endpoint for user authentication.
//	 * Expects username and password in the request body.
//	 */
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody User loginRequest) {
//		String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
//		return ResponseEntity.ok(token);
//	}
//
//	/**
//	 * Signup endpoint for user registration.
//	 * Expects username, password, and roles in the request body.
//	 */
//	@PostMapping("/signup")
//	public ResponseEntity<String> signup(@RequestBody User signupRequest) {
//		String response = authService.signup(signupRequest);
//		return ResponseEntity.ok(response);
//	}
//}

import com.example.demo.models.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController{

	@Autowired
	private final AuthService authService;

	public AuthController (AuthService authService){
		this.authService=authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		String response= authService.signup(user);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody User user){
		String token= authService.login(user.getUsername(), user.getPassword());
		return ResponseEntity.ok(token);
	}


}