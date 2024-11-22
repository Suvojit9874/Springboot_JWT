package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import java.util.Set;

@Configuration
public class SampleUserConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User(
                "admin",
                passwordEncoder.encode("password"),
                Set.of("ADMIN", "USER")
            ));
        };
    }
}