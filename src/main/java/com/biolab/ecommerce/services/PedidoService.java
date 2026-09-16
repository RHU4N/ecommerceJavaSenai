package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.entities.enums.StatusPedido;
import com.biolab.ecommerce.repositories.PedidoRepository;
import com.biolab.ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public String salvarPedido(PedidoDTO dto) {
        Pedido p = new Pedido();
        Usuario u = usuarioRepository.findById(dto.getIdCliente())
                .orElseThrow();
        p.setCliente(u);
        p.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        p.setMomento(Instant.now());
        pedidoRepository.save(p);
        return "Pedido salvo com sucesso";
    }

    public List<PedidoDTO> getAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido p : pedidos) {
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setId(p.getId());
            pedidoDTO.setMomento(p.getMomento());
            pedidoDTO.setStatus(p.getStatus());
            pedidoDTO.setIdCliente(p.getCliente().getId());
            pedidosDTO.add(pedidoDTO);
        }
        return pedidosDTO;
    }

    public PedidoDTO getById(long id){
        Pedido p = pedidoRepository.findById(id).orElseThrow();
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(p.getId());
        pedidoDTO.setMomento(p.getMomento());
        pedidoDTO.setStatus(p.getStatus());
        pedidoDTO.setIdCliente(p.getCliente().getId());
        return pedidoDTO;
    }

    public PedidoDTO update(long id, PedidoDTO dto){
        Pedido p = pedidoRepository.findById(id).orElseThrow();
        Usuario u = usuarioRepository.findById(dto.getIdCliente()).orElseThrow();
        p.setMomento(dto.getMomento());
        p.setStatus(dto.getStatus());
        p.setCliente(u);
        pedidoRepository.save(p);
        return dto;
    }

    public String deletarPedido(long id) {
        Pedido p = pedidoRepository.findById(id).orElseThrow();
        pedidoRepository.delete(p);
        return "Pedido deletado com sucesso!";
    }

}
