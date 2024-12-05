package br.unitins.topicos1.dto.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartaoCreditoDTO (
    @NotBlank(message = "O número do cartão é obrigatório")
    String numeroCartao,
    @NotBlank(message = "O nome do titular é obrigatório")
    String nomeTitular,
    @NotBlank(message = "A data de validade é obrigatória")
    String dataValidade,
    @NotBlank(message = "O CVV é obrigatório")
    String cvv,
    @NotNull(message = "O id do pedido é obrigatório")  
    Long idPedido
) {
    
}
