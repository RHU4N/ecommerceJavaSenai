package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.UsuarioDTO;
import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<?> getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deleteUsuario(id));
    }
}
