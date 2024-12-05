package br.unitins.topicos1.dto.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PixDTO (
    @NotBlank(message = "A chave PIX é obrigatória")
    String chavePix,
    @NotNull(message = "O id do pedido é obrigatório")
    Long idPedido
){
    
}
