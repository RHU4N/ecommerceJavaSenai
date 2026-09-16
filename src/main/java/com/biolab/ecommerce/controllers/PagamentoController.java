package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.PagamentoDTO;
import com.biolab.ecommerce.entities.Pagamento;
import com.biolab.ecommerce.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody @Valid PagamentoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criarPagamento(dto));
    }

    @GetMapping
    public ResponseEntity<?> getPagamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPagamento(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarPagamento(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePagamento(@PathVariable long id ,@RequestBody @Valid PagamentoDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.updatePagamento(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePagamento(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pagamentoService.deletarPagamento(id));
    }

}
