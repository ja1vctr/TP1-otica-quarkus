package br.unitins.topicos1.dto.pedido;

import br.unitins.topicos1.model.pedido.Endereco;

public record EnderecoResponseDTO (
    Long id,
    String cep,
    String estado,
    String cidade,
    String bairro,
    String rua,
    String numero   
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
            endereco.getId(),
            endereco.getCep(),
            endereco.getEstado(),
            endereco.getCidade(),
            endereco.getBairro(),
            endereco.getRua(),
            endereco.getNumero()
        );
    }	
}
