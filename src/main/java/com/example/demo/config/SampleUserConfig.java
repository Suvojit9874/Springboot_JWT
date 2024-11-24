package com.example.demo.config;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class SampleUserConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initSampleUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the admin user already exists, if not create it
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        Set.of(Role.ROLE_ADMIN)  // Use enum Role directly
                );
                userRepository.save(admin);
            }

            // Check if the regular user exists, if not create it
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User(
                        "user",
                        passwordEncoder.encode("user123"),
                        Set.of(Role.ROLE_USER)  // Use enum Role directly
                );
                userRepository.save(user);
            }
        };
    }
}
