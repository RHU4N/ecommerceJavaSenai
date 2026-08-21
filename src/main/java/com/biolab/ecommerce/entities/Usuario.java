package com.biolab.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Blob;

//Substitui getter and setter
@Data
//Substitui construtor cheio
@AllArgsConstructor
//Substitui construtor vazio
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    @NotBlank @Email
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false, length = 20)
    private String telefone;

    @NotBlank @Size(min = 6, max = 20)
    @Column(nullable = false, length = 150)
    private String senha;

    @NotBlank
    private String[] roles;

}
