package com.edutech.progressive.jwt;

import com.edutech.progressive.entity.User;
import com.edutech.progressive.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final UserRepository userRepository;

    private final String secret = "secretKey0000_secretKey0000_secretKey0000_secretKey0000";
    private final int expiration = 86400;

    private final SecretKey key;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            claims.put("role", user.getRole());
            claims.put("userId", user.getUserId());
            claims.put("patientId", user.getPatient() != null ? user.getPatient().getPatientId() : null);
            claims.put("doctorId", user.getDoctor() != null ? user.getDoctor().getDoctorId() : null);
        }
        Date now = new Date();
        Date exp = new Date(now.getTime() + (expiration * 1000L));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        String t = token.startsWith("Bearer ") ? token.substring(7) : token;
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(t)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractAllClaims(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}