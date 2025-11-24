package com.NRG.vende.dto.produto;

public record ProdutoRequestDTO(
        long id,
        String nome,
        String preco_compra,
        String preco_venda,
        int estoque_atual,
        int estoque_minimo
) {
}
