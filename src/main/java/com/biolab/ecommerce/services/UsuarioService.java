package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.UsuarioDTO;
import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.entities.enums.Role;
import com.biolab.ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String criar(UsuarioDTO u){
        Usuario usuario = new Usuario();
        usuario.setNome(u.getNome());
        usuario.setEmail(u.getEmail());
        usuario.setTelefone(u.getTelefone());
        usuario.setSenha(u.getSenha());
        usuario.setRoles(u.getRoles() != null ? u.getRoles() : Role.USER);
        usuarioRepository.save(usuario);
        return "Usuario criado com sucesso!";
    }

    public List<UsuarioDTO> getUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario u : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNome(u.getNome());
            usuarioDTO.setEmail(u.getEmail());
            usuarioDTO.setTelefone(u.getTelefone());
            usuarioDTO.setSenha(u.getSenha());
            usuarioDTO.setRoles(u.getRoles());
            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }

}
