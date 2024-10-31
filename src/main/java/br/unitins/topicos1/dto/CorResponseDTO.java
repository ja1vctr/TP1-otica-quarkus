package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cor;

public record CorResponseDTO(
        Long id,
        String nome,
        String indice
) {
    public static CorResponseDTO valueOf(Cor cor){
        return new CorResponseDTO(
                cor.getId(), 
                cor.getNome(),
                cor.getIndice()
        );
    }
}
