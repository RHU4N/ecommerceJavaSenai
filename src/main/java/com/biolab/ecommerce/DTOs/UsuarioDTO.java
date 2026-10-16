package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.enums.Role;
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
    @NotBlank
    @Email
    @Size(max = 150)
    private String email;
    @Size(max = 20, message = "O máximo são 20 caracteres")
    private String telefone;
    // Opcional em atualizações; obrigatório no cadastro, validado no service.
    @Size(min = 6, max = 72)
    private String senha;
    private Role roles;

    public UsuarioDTO(String nome, String email, String telefone, String senha, Role roles) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.roles = roles;
    }
}
