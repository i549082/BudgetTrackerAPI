package com.BudgetTracker.BudgetTrackerAPI.Security.JWT;

import com.BudgetTracker.BudgetTrackerAPI.Logic.Models.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final String  SECRET_KEY = "ec250c8d5e4414a6a74baedbf85613566d0e71739a80cb01926da993adbcb818";
    private final String ROLE_CLAIM = "role";
    private final String USER_ID_CLAIM = "userId";

    private Claims extractAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

    public String generateToken(Person person){
        String token = Jwts
                .builder()
                .subject(person.getUsername())
                .claim(ROLE_CLAIM, person.getRole().name())
                .claim(USER_ID_CLAIM, person.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSignInKey())
                .compact();
        return token;
    }

    public String extractUsername(String token) {
        return extractAllClaimsFromToken(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaimsFromToken(token).get(ROLE_CLAIM, String.class);
    }

    public Long extractUserId(String token) {
        return extractAllClaimsFromToken(token).get(USER_ID_CLAIM, Long.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractAllClaimsFromToken(token).getExpiration();
    }

    public Date extractIssuedAt(String token) {
        return extractAllClaimsFromToken(token).getIssuedAt();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
