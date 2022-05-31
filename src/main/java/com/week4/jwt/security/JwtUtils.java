package com.week4.jwt.security;

import com.week4.jwt.model.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.week4.jwt.config.SecurityConfig.EXPIRATION;
import static com.week4.jwt.config.SecurityConfig.SECRET;


@Component
public class JwtUtils {


  public String generateToken(Authentication authentication){
    User user = (User) authentication.getPrincipal();
    Date now = new Date(System.currentTimeMillis());

    Date expiryDate = new Date(now.getTime()+ EXPIRATION);

    String userId = Long.toString(user.getId());

    Map<String, Object> claims = new HashMap<>();
    claims.put("id", (Long.toString(user.getId())));
    claims.put("username", user.getUsername());
    claims.put("email",user.getEmail());

    return Jwts.builder()
        .setSubject(userId)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }


  public boolean validateToken(String token){
    try{
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
      return true;
    }catch(SignatureException ex){
      System.out.println("Invalid JWT Signature");
    }catch(MalformedJwtException ex){
      System.out.println("Invalid JWT Token");
    }catch(ExpiredJwtException ex){
      System.out.println("Unsupported JWT Token");
    }catch(IllegalArgumentException ex){
      System.out.println("JWT claims string is empty");
    }
    return false;
  }


  public Long getUserId(String token){
    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    String id = (String) claims.get("id");
    return Long.parseLong(id);
  }
}
