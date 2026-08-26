package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.repositories.PedidoRepository;
import com.biolab.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody @Valid PedidoDTO pedido) {
        return ResponseEntity.ok(pedidoService.salvarPedido(pedido));
    }
}
