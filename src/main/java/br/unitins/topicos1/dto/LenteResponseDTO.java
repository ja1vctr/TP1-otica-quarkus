package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Lente;

public record LenteResponseDTO(
        Long id,
        String tipoLente,
        String material,
        Double indiceRefracao,
        String tratamento,
        String categoriaFiltroSolar,
        Double astgmatiosmo,
        Double miopia,
        Double hipermetropia
) {
    public static LenteResponseDTO valueOf (Lente lente){
        return new LenteResponseDTO(
                lente.getId(),
                lente.getTipoLente(),
                lente.getMaterial(),
                lente.getIndiceRefracao(),
                lente.getTratamento(),
                lente.getCategoraiFiltroSolar(),
                lente.getAstgmatismo(),
                lente.getMiopia(),
                lente.getHipermetropia()
        );
    }
}
