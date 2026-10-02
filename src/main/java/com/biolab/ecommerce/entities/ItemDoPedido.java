package com.biolab.ecommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ItemDoPedido {
    private int quantidade;
    private double preco;

    @EmbeddedId
    private ItemDoPedidoPK id = new ItemDoPedidoPK();


    public ItemDoPedido(int quantidade, double preco, Pedido pedido,Produto produto) {
        this.quantidade = quantidade;
        this.preco = preco;
        id.setPedido(pedido);
        id.setProduto(produto);
    }

    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

}
