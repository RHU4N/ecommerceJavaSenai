package com.biolab.ecommerce.entities;

import com.biolab.ecommerce.entities.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Substitui getter and setter
@Data
//Substitui construtor cheio
@AllArgsConstructor
//Substitui construtor vazio
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Instant momento;

    private StatusPedido status;

    @ManyToOne
    private Usuario cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @OneToMany(
            mappedBy = "id.pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ItemDoPedido> itens = new HashSet<>();

    public List<Produto> getProdutos() {
        return itens.stream().map(x -> x.getProduto()).toList();
    }

}
