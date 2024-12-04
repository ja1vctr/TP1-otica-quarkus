package br.unitins.topicos1.dto.pedido;

public record ProdutoDTO (
    Double preco,
    String nome,
    Integer status,
    Integer quantidade,
    String tamanho,
    String tipo,
    String material,
    Long marca,
    String nomeImagem
){}
