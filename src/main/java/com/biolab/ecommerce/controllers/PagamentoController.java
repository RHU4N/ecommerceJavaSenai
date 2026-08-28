package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.PagamentoDTO;
import com.biolab.ecommerce.entities.Pagamento;
import com.biolab.ecommerce.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
