package com.example.spring_security_jwt.jwt;

import com.example.spring_security_jwt.security.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenProvider {
    @Value("${gin.jwt.secret}")
    String JWT_SECRET;

    @Value("${gin.jwt.expiration}")
    int JWT_EXPIRATION;

    public String generateToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder().setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
    }

    public String getUserNameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
            return true;
        }catch(MalformedJwtException e){
           log.error("Invalid JWT token");
        }catch(ExpiredJwtException e){
            log.error("Expired JWT token");
        }catch(UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
