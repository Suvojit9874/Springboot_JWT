package com.example.demo.config;//package com.example.demo.config;
//
//import com.example.demo.models.Role;
//import com.example.demo.models.User;
//import com.example.demo.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SampleUserConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public CommandLineRunner initSampleUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Check if the admin user already exists, if not create it
//            if (userRepository.findByUsername("admin").isEmpty()) {
//                User admin = new User(
//                        "admin",
//                        passwordEncoder.encode("admin123"),
//                        Role.ROLE_ADMIN  // Use enum Role directly for a single role
//                );
//                userRepository.save(admin);
//            }
//
//            // Check if the regular user exists, if not create it
//            if (userRepository.findByUsername("user").isEmpty()) {
//                User user = new User(
//                        "user",
//                        passwordEncoder.encode("user123"),
//                        Role.ROLE_USER  // Use enum Role directly for a single role
//                );
//                userRepository.save(user);
//            }
//        };
//    }
//}

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SampleUserConfig{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                User admin=new User(
                        "admin", passwordEncoder.encode("admin123"), Role.ROLE_ADMIN
                );
                userRepository.save(admin);
            }
            if(userRepository.findByUsername("user").isEmpty()){
                User user=new User(
                        "user",passwordEncoder.encode("user123"),Role.ROLE_USER
                );
                userRepository.save(user);
            }
        };
    }
}