package br.unitins.topicos1.Service.pedido;

import br.unitins.topicos1.dto.pagamento.CartaoCreditoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.pagamento.PixDTO;
import br.unitins.topicos1.model.pedido.Pagamento;

public interface PagamentoService {
    PagamentoResponseDTO pagarPix ( PixDTO dto);
    PagamentoResponseDTO pagarCredito ( CartaoCreditoDTO dto);
    PagamentoResponseDTO findById ( Long id);
    Pagamento findByIdPedido ( Long id);
}
