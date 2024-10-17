package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Status;

public record ArmacaoResponseDTO(
        Long id,
        Double preco,
        String nome,
        Status status,
        String material,
        String tamanho,
        String formato,
        String curvaLente,
        String modelo,
        CorResponseDTO cor,
        MarcaResponseDTO marca
) {
    public static ArmacaoResponseDTO valueOf(Armacao armacao){
        return new ArmacaoResponseDTO(
                armacao.getId(),
                armacao.getPreco(),
                armacao.getNome(),
                armacao.getStatus(),
                armacao.getMaterial(),
                armacao.getTamanho(),
                armacao.getFormato(),
                armacao.getCurvaLente(),
                armacao.getModelo(),
                CorResponseDTO.valueOf(armacao.getCor()),
                MarcaResponseDTO.valueOf(armacao.getMarca())
        );
    }
}
