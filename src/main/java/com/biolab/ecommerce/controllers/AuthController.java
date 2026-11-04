package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.LoginDTO;
import com.biolab.ecommerce.config.SecurityConfig;
import com.biolab.ecommerce.services.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public AuthController(CustomUserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }


    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO data) {
        try {
            var u = userDetailsService.loadUserByUsername(data.getEmail());
            if (!encoder.matches(data.getSenha(), u.getPassword())) {
                return ResponseEntity.ok("Usuario ou senha invalido");
            }else{
                return ResponseEntity.ok("Logado com sucesso");
            }
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.ok("Usuario não encontrado");
        }
    }

}
