package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.repositories.PedidoRepository;
import com.biolab.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> getPedido(){
        return ResponseEntity.ok(pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable long id){
        return ResponseEntity.ok(pedidoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable long id, @RequestBody @Valid PedidoDTO pedido) {
        return ResponseEntity.ok().body(pedidoService.update(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pedidoService.deletarPedido(id));
    }
}
