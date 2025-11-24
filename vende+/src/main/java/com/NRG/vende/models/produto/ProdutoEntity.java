package com.NRG.vende.models.produto;

import com.NRG.vende.dto.produto.ProdutoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String nome;
    @Column(nullable = false)
    @Min(0)
    private int estoque_atual = 0;
    @Column(nullable = false)
    @Min(0)
    private int estoque_minimo = 0;
    @Column(nullable = false)
    @DecimalMin("0")
    private BigDecimal preco_venda;
    @Column(nullable = false)
    @DecimalMin("0")
    private BigDecimal preco_compra;

    public ProdutoEntity() {}

    public ProdutoEntity(String nome, int estoque_atual, int estoque_minimo, String preco_venda, String preco_compra) {
        this.nome = nome;
        this.estoque_atual = estoque_atual;
        this.estoque_minimo = estoque_minimo;
        if (this.validarPreco(preco_venda) || this.validarPreco(preco_compra)) {
            this.preco_venda = new BigDecimal(preco_venda);
            this.preco_compra = new BigDecimal(preco_compra);
        }
    }

    public ProdutoEntity(ProdutoRequestDTO data) {
        var p_c = new BigDecimal(data.preco_compra());
        var p_v = new BigDecimal(data.preco_venda());

        if (data.estoque_atual() < 0 || data.estoque_minimo() < 0 || p_v.compareTo(p_c) != 1) {
            throw new IllegalArgumentException("Valor(es) de produto invalido(s)!");
        }

        this.nome = data.nome();
        this.estoque_atual = data.estoque_atual();
        this.estoque_minimo = data.estoque_minimo();
        this.preco_compra = p_c;
        this.preco_venda = p_v;
    }

    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getEstoque_atual() {
        return estoque_atual;
    }
    public int getEstoque_minimo() { return estoque_minimo; }
    public BigDecimal getPreco_venda() { return preco_venda; }
    public BigDecimal getPreco_compra() { return preco_compra; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEstoque_atual(int estoque_atual) { this.estoque_atual = estoque_atual; }
    public void setEstoque_minimo(int estoque_minimo) { this.estoque_minimo = estoque_minimo;}

    public void setPreco_venda(String preco) {
        this.validarPreco(preco);
        this.preco_venda = new BigDecimal(preco);
    }

    public void setPreco_compra(String preco) {
        this.validarPreco(preco);
        this.preco_compra = new BigDecimal(preco);
    }

    public BigDecimal getLucro() {
        return preco_venda.subtract(preco_compra);
    }

    private boolean validarPreco(String preco) {
        var p = new BigDecimal(preco);
        var zero = new BigDecimal(0);
        if (p.compareTo(zero) == -1) {
            throw new IllegalArgumentException("Valor negativo para preço");
        }
        return true;
    }
}
