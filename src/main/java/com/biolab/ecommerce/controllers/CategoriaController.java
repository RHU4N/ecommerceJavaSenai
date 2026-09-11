package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.CategoriaDTO;
import com.biolab.ecommerce.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCat(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.createCategoria(dto));
    }

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findCategoriaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable long id, @RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.updateCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoriaService.deleteCategoria(id));
    }
}
