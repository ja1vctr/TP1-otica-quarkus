package br.unitins.topicos1.dto;

public record AcessorioDTO(
    Double preco,
    String nome,
    Integer status,
    Integer quantidade,
    String descricao
) {
}
