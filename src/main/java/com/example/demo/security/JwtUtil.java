package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;
@Component
public class JwtUtil {

	 private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	  private static final long TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
	  public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
	                .signWith(SECRET_KEY) // Use secure key
	                .compact();
	    }

	    /**
	     * Validates the token and extracts the username.
	     * 
	     * @param token The JWT token.
	     * @return The username if the token is valid, else null.
	     */
	  public boolean validateToken(String token) {
		    try {
		        Claims claims = Jwts.parser()
		                .setSigningKey(SECRET_KEY) // Use your secret key
		                .parseClaimsJws(token)
		                .getBody();
		        return claims.getExpiration().after(new Date()); // Check if token is not expired
		    } catch (JwtException | IllegalArgumentException e) {
		        return false; // Token is invalid
		    }
		}

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

  
}