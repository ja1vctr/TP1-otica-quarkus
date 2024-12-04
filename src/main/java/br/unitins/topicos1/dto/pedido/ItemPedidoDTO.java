package br.unitins.topicos1.dto.pedido;

import jakarta.validation.constraints.NotNull;

public record ItemPedidoDTO (
    @NotNull(message = "A quantidade deve ser informada")
    Integer quantidade,
    @NotNull(message = "O preço unitário deve ser informado")
    Double precoUnitario,
    @NotNull(message = "O id do produto deve ser informado")
    Long idProduto
) {}
