package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Acessorio;
import br.unitins.topicos1.model.Status;

public record AcessorioResponseDTO(
        Long id,
        Double preco,
        String nome,
        Status status,
        Integer quantidade,
        String descricao
) {
    public static AcessorioResponseDTO valueOf (Acessorio acessorio){
        return new AcessorioResponseDTO(
                acessorio.getId(),
                acessorio.getPreco(),
                acessorio.getNome(),
                acessorio.getStatus(),
                acessorio.getQuantidade(),
                acessorio.getDescricao()
        );
    }
}
