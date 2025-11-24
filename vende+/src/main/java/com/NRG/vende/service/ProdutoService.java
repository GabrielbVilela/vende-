package com.NRG.vende.service;

import com.NRG.vende.dto.produto.ProdutoRequestDTO;
import com.NRG.vende.models.produto.ProdutoEntity;
import com.NRG.vende.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository r;

    public void criarProduto(ProdutoRequestDTO data) {
        var produto = new ProdutoEntity(data);
        r.save(produto);
    }

    public List<ProdutoEntity> listarProdutos() {
        return r.findAll();
    }

    public ProdutoEntity buscarProdutoPorId(Long id) {
        return r.findById(id).get();
    }

    public void atualizarProduto(Long id, ProdutoRequestDTO data) {
        var produto = buscarProdutoPorId(id);
        this.atualizarCampos(produto, data);
        r.save(produto);
    }

    private void atualizarCampos(ProdutoEntity produto, ProdutoRequestDTO dto) {
        if (dto.nome() != null) {
            produto.setNome(dto.nome());
        }
        if (dto.preco_compra() != null) {
            produto.setPreco_compra(dto.preco_compra());
        }
        if (dto.preco_venda() != null) {
            produto.setPreco_venda(dto.preco_venda());
        }
        if (dto.estoque_atual() > 0) {
            produto.setEstoque_atual(dto.estoque_atual());
        }
        if (dto.estoque_minimo() > 0) {
            produto.setEstoque_minimo(dto.estoque_minimo());
        }
        if (dto.preco_venda() != null) {
            validarPreco(dto.preco_venda());
            produto.setPreco_venda(dto.preco_venda());
        }
        if (dto.preco_compra() != null) {
            validarPreco(dto.preco_compra());
            produto.setPreco_compra(dto.preco_compra());
        }
    }

    private void validarPreco(String preco) {
        BigDecimal precoDecimal = new BigDecimal(preco);
        if (precoDecimal.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("O preço está negativo!");
        }
    }

    @PostConstruct
    public void init() {
        r.save(new ProdutoEntity("Notebook Dell Inspiron",1001,15,"5249.50","3499.90"));
        r.save(new ProdutoEntity("Mouse Logitech MX Master",45,1002,"349.55","299.99"));
        r.save(new ProdutoEntity("Teclado Mecânico RGB",32,1003,"471.80","459.90"));
        r.save(new ProdutoEntity("Monitor LG 27 Polegadas",8,1004,"1539.00","1299.00"));
        r.save(new ProdutoEntity("Webcam Full HD",23,1005,"436.70","189.90"));
        r.save(new ProdutoEntity("Headset Gamer HyperX",18,1006,"629.20","349.90"));
    }


}
