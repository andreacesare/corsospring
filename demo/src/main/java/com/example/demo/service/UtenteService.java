package com.example.demo.service;

import com.example.demo.JwtUtil;
import com.example.demo.entity.Utente;
import com.example.demo.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public Utente findByUsername(String username){
        return utenteRepository.findByUsername(username).orElse(null);
    }

    public Utente registraUtente(String username, String password){
        Utente newUtente=new Utente();
        if(utenteRepository.findByUsername(username).isEmpty()){
            newUtente.setUsername(username);
            newUtente.setPassword(encoder.encode(password));
            utenteRepository.save(newUtente);
            return newUtente;
        } else{throw new IllegalArgumentException();}
    }

    public ResponseEntity<Map<String,String>> login(String username, String password){
        Utente utente = findByUsername(username);
        if(utente==null ||  !encoder.matches(password, utente.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid username or password"));
        }
        String token=jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }


}
