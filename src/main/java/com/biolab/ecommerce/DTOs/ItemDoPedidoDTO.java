package com.biolab.ecommerce.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDoPedidoDTO {
    private int quantidade;
    private double preco;
    private long idPedido;
    private long idProduto;

}
