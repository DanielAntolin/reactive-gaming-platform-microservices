package com.example.authverificacionapi.Services.Impl;

import com.example.authverificacionapi.Services.JwtService;
import com.example.authverificacionapi.common.dtos.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public TokenResponse generateToken(Long userId, String role ) {
        Date expirateDay = new Date(Long.MAX_VALUE);
        String token = Jwts.builder()
                .setExpiration(expirateDay)
                .signWith(SignatureAlgorithm.HS512, this.secretToken)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .compact();


        return TokenResponse.builder()
                .accessToken(token)
                .build();

    }

    @Override
    public Claims getClaims(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(this.secretToken)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token malformado: " + token, e);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el token", e);
        }
    }

    @Override
    public boolean isTokenExpired(String token) {
        try{
            return  getClaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Integer extractUserId(String token) {
        System.out.println("Token recibido para extracción de ID: " + token);
        try {
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el ID de usuario del token", e);
        }

    }
    public String extractUserRole(String token) {
        return getClaims(token).get("role", String.class);
    }

}
