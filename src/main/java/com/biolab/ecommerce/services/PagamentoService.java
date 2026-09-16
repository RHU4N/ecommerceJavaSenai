package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.PagamentoDTO;
import com.biolab.ecommerce.controllers.PedidoController;
import com.biolab.ecommerce.entities.Pagamento;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.repositories.PagamentoRepository;
import com.biolab.ecommerce.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public List<PagamentoDTO> listarPagamentos(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<PagamentoDTO> listaPagamentos = new ArrayList<>();
        for (Pagamento p : pagamentos) {
            PagamentoDTO pagamentoDTO = new PagamentoDTO();
            pagamentoDTO.setId(p.getId());
            pagamentoDTO.setMomento(p.getMomento());
            pagamentoDTO.setIdPedido(p.getPedido().getId());
            listaPagamentos.add(pagamentoDTO);
        }
        return listaPagamentos;
    }

    public PagamentoDTO buscarPagamento(long id){
        Pagamento p = pagamentoRepository.findById(id).orElseThrow();
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(p.getId());
        pagamentoDTO.setMomento(p.getMomento());
        pagamentoDTO.setIdPedido(p.getPedido().getId());
        return pagamentoDTO;
    }

    public PagamentoDTO updatePagamento(long id, PagamentoDTO dto){
        Pagamento p = pagamentoRepository.findById(id).orElseThrow();
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido()).orElseThrow();
        p.setMomento(dto.getMomento());
        p.setPedido(pedido);
        pagamentoRepository.save(p);
        return dto;
    }

    public String deletarPagamento(long id){
        pagamentoRepository.deleteById(id);
        return "Pagamento Deletado com sucesso!";
    }


}
