package com.biolab.ecommerce.controllers;

import com.biolab.ecommerce.DTOs.ItemDoPedidoDTO;
import com.biolab.ecommerce.entities.ItemDoPedido;
import com.biolab.ecommerce.services.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Itens")
public class ItemDoPedidoController {
    private final ItemPedidoService itemPedidoService;

    public ItemDoPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ItemDoPedidoDTO dto){
        return ResponseEntity.ok(itemPedidoService.create(dto));
    }

    @DeleteMapping("/{idPedido}/{idProduto")
    public ResponseEntity<?> delete(@PathVariable long idPedido,@PathVariable long idProduto){
        itemPedidoService.Delete(idPedido, idProduto);
        return ResponseEntity.noContent().build();
    }
}
