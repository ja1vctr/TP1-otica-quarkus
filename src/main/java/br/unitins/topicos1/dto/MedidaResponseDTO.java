package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Medida;

public record MedidaResponseDTO(
        Long id,
        String descricao
) {
    public static MedidaResponseDTO valueOf(Medida medida){
        return new MedidaResponseDTO(
                medida.getId(),
                medida.getDescricao());
    }
}
