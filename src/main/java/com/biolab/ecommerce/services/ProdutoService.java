package com.biolab.ecommerce.services;

import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.entities.Categoria;
import com.biolab.ecommerce.entities.Produto;
import com.biolab.ecommerce.repositories.CategoriaRepository;
import com.biolab.ecommerce.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public String create(ProdutoDTO dto){
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setPreco(dto.getPreco());
        p.setImgUrl(dto.getImgUrl());

        Categoria cat = categoriaRepository.getReferenceById(dto.getIdCategoria());
        p.getCategorias().add(cat);

        produtoRepository.save(p);

        return "Produto criado com sucesso!";
    }

    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> dtos = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoDTO dto = new ProdutoDTO();
            dto.setNome(produto.getNome());
            dto.setDescricao(produto.getDescricao());
            dto.setPreco(produto.getPreco());
            dto.setImgUrl(produto.getImgUrl());
            dto.setIdCategoria(produto.getCategorias().iterator().next().getId());
            dtos.add(dto);
        }
        return dtos;
    }

    public ProdutoDTO findById(long id){
        Produto prod = produtoRepository.findById(id).orElseThrow();
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome(prod.getNome());
        dto.setDescricao(prod.getDescricao());
        dto.setPreco(prod.getPreco());
        dto.setImgUrl(prod.getImgUrl());
        dto.setIdCategoria(prod.getCategorias().iterator().next().getId());
        return dto;
    }

    public ProdutoDTO update(long id, ProdutoDTO dto){
        Produto prod = produtoRepository.findById(id).orElseThrow();
        prod.setNome(dto.getNome());
        prod.setDescricao(dto.getDescricao());
        prod.setPreco(dto.getPreco());
        prod.setImgUrl(dto.getImgUrl());
        Categoria cat = categoriaRepository.findById(dto.getIdCategoria()).orElseThrow();
        prod.setCategorias((Set<Categoria>) cat);
        produtoRepository.save(prod);
        return dto;
    }

    public String delete(long id){
        produtoRepository.deleteById(id);
        return "Produto deletado com sucesso!";
    }

}
