package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.PagamentoDTO;
import com.biolab.ecommerce.controllers.PedidoController;
import com.biolab.ecommerce.entities.Pagamento;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.repositories.PagamentoRepository;
import com.biolab.ecommerce.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public String criarPagamento(PagamentoDTO dto) {
        Pedido p = pedidoRepository.findById(dto.getIdPedido()).orElseThrow();
        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(p);
        pagamento.setMomento(Instant.now());
        pagamentoRepository.save(pagamento);
        return "Pagamento Realizado com sucesso!";
    }


}
