package com.NRG.vende.models;

import jakarta.persistence.*;

@Entity
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String nome;
    private int quantidade;

    public ProdutoEntity() {
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
