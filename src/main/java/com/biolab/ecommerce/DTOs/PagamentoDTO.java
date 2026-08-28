package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.Pedido;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    private long id;
    private Instant momento;
    private long idPedido;

    public PagamentoDTO(long idPedido) {
        this.momento = Instant.now();
        this.idPedido = idPedido;
    }
}
