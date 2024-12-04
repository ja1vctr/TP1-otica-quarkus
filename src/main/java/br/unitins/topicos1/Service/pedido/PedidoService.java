package br.unitins.topicos1.Service.pedido;

import java.util.List;

import br.unitins.topicos1.dto.pedido.PedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO findById(Long id);

    List<PedidoResponseDTO> findByUserId(Long id);
    
    PedidoResponseDTO create(PedidoDTO dto);
    
    // implementar os patch's
}
