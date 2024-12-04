package br.unitins.topicos1.dto.pedido;

import java.util.List;

import jakarta.validation.constraints.NotNull;
public record PedidoDTO (
    @NotNull(message = "O valor total não pode ser nulo")
    Double valorTotal,
    @NotNull(message = "O endereço de entrega não pode ser nulo")
    Long enderecoEntrega,
    @NotNull(message = "O usuário não pode ser nulo")
    Long usuario, 
    @NotNull(message = "A lista de itens do pedido não pode ser nula")
    List<ItemPedidoDTO> listaItemPedido
) {}