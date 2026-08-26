package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.entities.enums.StatusPedido;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private long id;
    private Instant momento;
    private StatusPedido status;
    private Long idCliente;

    public PedidoDTO(Instant momento, StatusPedido status, Long idCliente) {
        this.momento = Instant.now();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.idCliente = idCliente;
    }
}
