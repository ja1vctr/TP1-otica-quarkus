package br.unitins.topicos1.dto.pagamento;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;
import br.unitins.topicos1.model.pedido.Pagamento;
import br.unitins.topicos1.model.pedido.TipoPagamento;

public record PagamentoResponseDTO (
    Long id,
    Double valor,
    TipoPagamento tipoPagamento,
    LocalDateTime dataPagamento,
    Boolean statusPagamento,
    PedidoResponseDTO pedido
) {
  public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
    return new PagamentoResponseDTO(
      pagamento.getId(),
      pagamento.getValor(),
      pagamento.getTipoPagamento(),
      pagamento.getDataConfirmacaoPagamento(),
      pagamento.getStatusPagamento(),
      PedidoResponseDTO.valueOf(pagamento.getPedido())
    );
  }  
}
