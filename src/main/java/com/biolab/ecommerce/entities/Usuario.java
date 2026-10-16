package com.biolab.ecommerce.entities;

import com.biolab.ecommerce.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role roles;
}
