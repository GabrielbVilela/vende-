package com.NRG.vende.controller;

import com.NRG.vende.models.ProdutoEntity;
import com.NRG.vende.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/produto")
    public List<ProdutoEntity> produto() {
        return produtoRepository.findAll();
    }

    @PostMapping("/produto")
    public ResponseEntity<String> post(@RequestBody ProdutoEntity produto) {
        produtoRepository.save(produto);
        return ResponseEntity.ok("cadastrado com sucesso");
    }
}
