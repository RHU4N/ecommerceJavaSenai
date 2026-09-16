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
            usuarioDTO.setRoles(u.getRoles());
            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }

    public UsuarioDTO getUsuario(long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setRoles(usuario.getRoles());
        return usuarioDTO;
    }

    public UsuarioDTO updateUsuario(long id, UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setRoles(usuarioDTO.getRoles());
        usuarioRepository.save(usuario);
        return usuarioDTO;
    }

    public String deleteUsuario(long id){
        usuarioRepository.deleteById(id);
        return "Usuario deletado com sucesso!";
    }

}
