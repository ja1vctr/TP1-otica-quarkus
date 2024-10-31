package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Categoria;

public record ArmacaoResponseDTO(
        Long id,
        String nome,
        String material,
        String formato,
        String modelo,
        Categoria categoria,
        MedidaResponseDTO medida,
        CorResponseDTO cor,
        MarcaResponseDTO marca
) {
    public static ArmacaoResponseDTO valueOf(Armacao armacao){
        return new ArmacaoResponseDTO(
                armacao.getId(),
                armacao.getNome(),
                armacao.getMaterial(),
                armacao.getFormato(),
                armacao.getModelo(),
                armacao.getCategoria(),
                MedidaResponseDTO.valueOf(armacao.getMedida()),
                CorResponseDTO.valueOf(armacao.getCor()),
                MarcaResponseDTO.valueOf(armacao.getMarca())
        );
    }
}
