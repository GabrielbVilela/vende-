package com.NRG.vende.repository;

import com.NRG.vende.models.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findAll();
    ProdutoEntity findById(long id);
    List<ProdutoEntity> findByNomeContainingIgnoreCase(String nome);

}
