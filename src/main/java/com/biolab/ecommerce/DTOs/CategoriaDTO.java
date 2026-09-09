package com.biolab.ecommerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private long id;
    private String nome;
    private Long idProdutos;

    public CategoriaDTO(String nome, Long idProdutos) {
        this.nome = nome;
        this.idProdutos = idProdutos;
    }
}
