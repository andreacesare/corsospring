package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    public final String secretKey="jfkjsabfauihfw7t3w8t9ht49hhtnfy798y55tuguieuv75y88745yf87ny78re8f8d3tr74trc8b74rtc87r874nrt89tc84tr83q64ntc834trc864tr8nc34yrc837cbrt734ryc074ynt8q37t8n4tyc8734tcy874n874t8n347nt8347nyct8374tn87yt4837ny87nt7c8473cytv8704nv37nytc87bf5";

    public final Integer time=1000*60*60;


    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+time))
                .signWith(getSigninKey())
                .compact();
    }

    public String parseToken(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getSigninKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (ExpiredJwtException e) {
            System.out.println("Token scaduto: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("Token non valido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore sconosciuto nella validazione del token: " + e.getMessage());
        }
        return false;
        }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }





}
