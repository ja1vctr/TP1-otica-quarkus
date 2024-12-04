package br.unitins.topicos1.dto.pedido;

import java.util.List;

import br.unitins.topicos1.model.pedido.Pedido;
import br.unitins.topicos1.model.pedido.StatusPedido;
import br.unitins.topicos1.model.user.Usuario;

public record PedidoResponseDTO (
    Long id,
    Double valorTotal,
    EnderecoResponseDTO enderecoEntrega,
    Usuario usuario,
    List<ItemPedidoResponseDTO> listaItemPedido,
    StatusPedido statusPedido
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getValorTotal(),
            EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
            pedido.getUsuario(),
            pedido.getListaItensPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList(),
            pedido.getStatusPedido()
        );
    }
}
