package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.ItemDoPedidoDTO;
import com.biolab.ecommerce.entities.ItemDoPedido;
import com.biolab.ecommerce.entities.ItemDoPedidoPK;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.entities.Produto;
import com.biolab.ecommerce.repositories.ItemDoPedidoRepository;
import com.biolab.ecommerce.repositories.PedidoRepository;
import com.biolab.ecommerce.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemDoPedidoRepository itemDoPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemPedidoService(ItemDoPedidoRepository itemDoPedidoRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.itemDoPedidoRepository = itemDoPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }


    public ItemDoPedidoDTO create(ItemDoPedidoDTO dto) {
        ItemDoPedido itemDoPedido = new ItemDoPedido();
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido()).orElseThrow();
        Produto produto = produtoRepository.findById(dto.getIdProduto()).orElseThrow();
        itemDoPedido.setPedido(pedido);
        itemDoPedido.setProduto(produto);
        itemDoPedido.setQuantidade(dto.getQuantidade());
        itemDoPedido.setPreco(dto.getPreco());
        itemDoPedidoRepository.save(itemDoPedido);
        return dto;
    }

    public List<ItemDoPedidoDTO> findAll() {
        List<ItemDoPedido> itemDoPedidos = itemDoPedidoRepository.findAll();
        List<ItemDoPedidoDTO> dtos = new ArrayList<>();
        for (ItemDoPedido item : itemDoPedidos) {
            ItemDoPedidoDTO dto = new ItemDoPedidoDTO();
            dto.setIdPedido(item.getPedido().getId());
            dto.setIdProduto(item.getProduto().getId());
            dto.setQuantidade(item.getQuantidade());
            dto.setPreco(item.getPreco());
            dtos.add(dto);
        }
        return dtos;
    }

    public void Delete(long pedidoId, long produtoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();

        ItemDoPedidoPK id = new ItemDoPedidoPK();
        id.setPedido(pedido);
        id.setProduto(produto);

        if (itemDoPedidoRepository.existsById(id)) {
            itemDoPedidoRepository.deleteById(id);
        }

    }
}
