package br.unitins.topicos1.dto.pedido;

import br.unitins.topicos1.model.Produto;

public record ProdutoResumidoResponseDTO (
    Long id,
    String nome,
    Double prDouble
) {
    public static ProdutoResumidoResponseDTO valueOf(Produto produto) {
        return new ProdutoResumidoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getPreco()
        );
    }
}
