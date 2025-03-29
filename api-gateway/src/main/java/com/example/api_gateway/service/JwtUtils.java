package com.example.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtUtils {
    private final String secretKey = "dGVzdGtpdGFsa2RmZzM0NTY3ODkwYWJjZGVmZ2hpa2xsYW5vcWVyMTIzNDU2Nzg5MAsdlfsekuhfiuwesdkjfksuehfsekdfusdhbfgsdksfb";

    public Claims getClaims(String token){
       return  Jwts.parserBuilder()
               .setSigningKey(secretKey)
               .build()
               .parseClaimsJws(token)
               .getBody();
    }

    public boolean isexpired(String token){
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
    public Integer extractUserId(String token){
        try{

            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
    public String extractUserRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}
