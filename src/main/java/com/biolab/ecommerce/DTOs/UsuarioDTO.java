package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private int id;
    @NotBlank
    private String nome;
    @NotBlank @Email
    @Size(max = 150)
    private String email;
    @Size(max = 20, message = "O maximo são 20 chars")
    private String telefone;
    @Size(min = 6, max = 20)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role roles;

    public UsuarioDTO(String nome, String email, String telefone, String senha, Role roles) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.roles = roles;
    }
}
