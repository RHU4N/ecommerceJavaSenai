package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.UsuarioDTO;
import com.biolab.ecommerce.entities.Usuario;
import com.biolab.ecommerce.entities.enums.Role;
import com.biolab.ecommerce.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String criar(UsuarioDTO u) {
        if (u.getSenha() == null || u.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha obrigatória");
        }
        if (usuarioRepository.existsByEmailIgnoreCase(u.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(u.getNome());
        usuario.setEmail(u.getEmail());
        usuario.setTelefone(u.getTelefone());
        usuario.setSenha(passwordEncoder.encode(u.getSenha()));
        // Nunca aceitar privilégios administrativos enviados no cadastro público.
        usuario.setRoles(Role.USER);
        usuarioRepository.save(usuario);
        return "Usuario criado com sucesso!";
    }

    public List<UsuarioDTO> getUsuario() {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario u : usuarioRepository.findAll()) {
            usuarioDTOs.add(toDto(u));
        }
        return usuarioDTOs;
    }

    public UsuarioDTO getUsuario(long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return toDto(usuario);
    }

    public UsuarioDTO updateUsuario(long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        if (!usuario.getEmail().equalsIgnoreCase(dto.getEmail())
                && usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        if (dto.getRoles() != null) {
            usuario.setRoles(dto.getRoles());
        }
        usuarioRepository.save(usuario);
        return toDto(usuario);
    }

    public String deleteUsuario(long id) {
        usuarioRepository.deleteById(id);
        return "Usuario deletado com sucesso!";
    }

    private UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(Math.toIntExact(usuario.getId()));
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setRoles(usuario.getRoles());
        // Não expor hash nem senha na resposta.
        return dto;
    }
}
