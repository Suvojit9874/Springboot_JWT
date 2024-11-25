package com.example.demo.service;//package com.example.demo.service;
//
//import com.example.demo.models.Role;
//import com.example.demo.models.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.security.JwtUtil;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
//                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    /**
//     * Login method to authenticate user and generate JWT token.
//     */
//    public String login(String username, String password) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            return jwtUtil.generateToken(username); // Generate JWT token on successful authentication.
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Invalid credentials", e);
//        }
//    }
//
//    /**
//     * Signup method to register a new user with a single role.
//     */
//    public String signup(User user) {
//        // Check if the username already exists
//        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            throw new RuntimeException("Error: Username is already taken!");
//        }
//
//        // Validate and assign the role, using the Role enum directly
//        Role role = user.getRole();  // Get the single role from the user
//
//        // Validate the role
//        if (role == null || (role != Role.ROLE_ADMIN && role != Role.ROLE_USER)) {
//            throw new RuntimeException("Error: Invalid role! Allowed roles: ROLE_ADMIN, ROLE_USER");
//        }
//
//        // Encode the password and save the user
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
//        newUser.setRole(role); // Set the single role
//
//        userRepository.save(newUser); // Save the new user to the database
//        return "User registered successfully!";
//    }
//}

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthService (AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository,
                        PasswordEncoder passwordEncoder){
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public String login(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            return jwtUtil.generateToken(username);
        }
        catch (AuthenticationException e){
            throw new RuntimeException("Invalid Username", e);

        }
    }

    public String signup(User user){
        try{
            Optional <User> existingUser=userRepository.findByUsername(user.getUsername());
            if(existingUser.isPresent()){
                throw new RuntimeException("Error: Username is already taken");
            }

            Role role=user.getRole();

            if(role==null || (role!= Role.ROLE_ADMIN && role!=Role.ROLE_USER)){
                throw new RuntimeException("Role is not allowed except Admin and User");
            }

            User newUser=new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setRole(user.getRole());

            userRepository.save(newUser);
            return "User registered successfully";

        } catch (AuthenticationException e){
               throw new RuntimeException(e);
        }
    }
}