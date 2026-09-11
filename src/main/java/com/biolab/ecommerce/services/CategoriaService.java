package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.CategoriaDTO;
import com.biolab.ecommerce.entities.Categoria;
import com.biolab.ecommerce.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public String createCategoria(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoriaRepository.save(categoria);
        return "Categoria cadastrada com sucesso";
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> listaCategoriaDTO = new ArrayList<>();
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(categoria.getId());
            categoriaDTO.setNome(categoria.getNome());
            listaCategoriaDTO.add(categoriaDTO);
        }
        return listaCategoriaDTO;
    }

    public CategoriaDTO findCategoriaById(long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());
        return categoriaDTO;
    }

    public CategoriaDTO updateCategoria(long id,CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        categoria.setNome(dto.getNome());
        categoriaRepository.save(categoria);
        return dto;
    }

    public String deleteCategoria(long id) {
        categoriaRepository.deleteById(id);
        return "Categoria deletada";
    }
}
