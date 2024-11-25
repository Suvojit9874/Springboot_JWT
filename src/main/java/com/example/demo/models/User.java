package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // Renaming the table to avoid conflicts
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING) // Store the user's role as a string (e.g., "ROLE_ADMIN", "ROLE_USER")
	@Column(nullable = false)
	private Role role;  // Store the user's role using the Role enum

	/**
	 * Constructor for creating a new user instance with a role.
	 */
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * Returns the granted authorities for the user based on their role.
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(new SimpleGrantedAuthority(role.name())); // Grant authority based on the single role
	}
}
