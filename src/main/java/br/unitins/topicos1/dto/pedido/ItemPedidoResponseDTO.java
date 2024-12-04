package br.unitins.topicos1.dto.pedido;

import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.pedido.ItemPedido;

public record ItemPedidoResponseDTO (
    Long id,
    Integer quantidade,
    Double precoUnitario,
    Produto produto
){
    public static ItemPedidoResponseDTO valueOf(ItemPedido itemPedido){
        return new ItemPedidoResponseDTO(
            itemPedido.getId(),
            itemPedido.getQuantidade(),
            itemPedido.getPrecoUnitario(),
            itemPedido.getProduto()
        );
    }
}
