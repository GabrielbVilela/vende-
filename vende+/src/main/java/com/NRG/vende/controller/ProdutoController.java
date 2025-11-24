package com.NRG.vende.controller;

import com.NRG.vende.dto.produto.ProdutoRequestDTO;
import com.NRG.vende.models.produto.ProdutoEntity;
import com.NRG.vende.repository.ProdutoRepository;
import com.NRG.vende.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/produto")
    public List<ProdutoEntity> produto() {
        return service.listarProdutos();
    }

    @PostMapping("/produto")
    public ResponseEntity<String> post(@RequestBody ProdutoRequestDTO data) {
        service.criarProduto(data);
        return ResponseEntity.ok("cadastrado com sucesso");
    }

    @PutMapping("/produto")
    public ResponseEntity<String> put(@RequestBody ProdutoRequestDTO data) {
        service.atualizarProduto(data.id(), data);
        return ResponseEntity.ok("atualizado com sucesso");
    }
}
