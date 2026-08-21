package com.biolab.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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





}
