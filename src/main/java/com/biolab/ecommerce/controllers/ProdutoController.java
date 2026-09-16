package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.entities.Produto;
import com.biolab.ecommerce.services.ProdutoService;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@Valid @RequestBody ProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<?> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(produtoService.delete(id));
    }
}
