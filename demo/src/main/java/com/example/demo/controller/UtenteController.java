package com.example.demo.controller;

import com.example.demo.entity.Utente;
import com.example.demo.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("utente")
@RequiredArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;

    @PostMapping("/register")
    public Utente register(@RequestParam String username, @RequestParam String password) {
        return this.utenteService.registraUtente(username,password);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        return this.utenteService.login(username,password);
    }


}
