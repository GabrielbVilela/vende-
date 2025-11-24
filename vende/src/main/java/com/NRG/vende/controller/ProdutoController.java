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
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping()
    public List<ProdutoEntity> listaProduto() {
        return service.listarProdutos();
    }

    @PostMapping()
    public ResponseEntity<String> criaProduto(@RequestBody ProdutoRequestDTO data) {
        service.criarProduto(data);
        return ResponseEntity.ok("cadastrado com sucesso");
    }

    @PutMapping()
    public ResponseEntity<String> atualizaProduto(@RequestBody ProdutoRequestDTO data) {
        service.atualizarProduto(data.id(), data);
        return ResponseEntity.ok("atualizado com sucesso");
    }
}
