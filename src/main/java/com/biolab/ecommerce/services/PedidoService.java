package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.repositories.PedidoRepository;
import com.biolab.ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

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
        p.setStatus(dto.getStatus());
        p.setMomento(dto.getMomento());
        pedidoRepository.save(p);
        return "Pedido salvo com sucesso";
    }

}
