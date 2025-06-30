package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	private final static String SECRET_KEY = "ThisIsASecretKeyThisIsASecretKeyThisIsASecretKey";
	private final static long EXPIRATION = 48*60*60*1000L;
	
	
	public Claims extractAllClaims(String token) {
		
		return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		
	}
	
	public String extractUserName(String token) {
		
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
		
	}
	
	public Date extractExpiry(String token) {
		
		Claims claim = extractAllClaims(token);
		return claim.getExpiration();
		
	}
	
	public String generateToken(UserDetails user) {
		
		return Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET_KEY).addClaims(new HashMap<>())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).compact();
	}
	
	public boolean validateToken(String token, UserDetails user) {
		String username = extractUserName(token);
		Date expiry = extractExpiry(token);
		return (username.equals(user.getUsername()) && expiry.after(new Date(System.currentTimeMillis())));
	}

}
