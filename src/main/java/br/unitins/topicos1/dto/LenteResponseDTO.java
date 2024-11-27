package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.model.Status;

public record LenteResponseDTO(
        Long id,
        Double preco,
        String nome,
        Status status,
        Integer quantidade,
        String tamanho,
        String tipo,
        String material,
        MarcaResponseDTO marca,
        String tratamento,
        String espessura,
        String receita
) {
    public static LenteResponseDTO valueOf (Lente lente){
        return new LenteResponseDTO(
                lente.getId(),
                lente.getPreco(),
                lente.getNome(),
                lente.getStatus(),
                lente.getQuantidade(),
                lente.getTamanho(),
                lente.getTipo(),
                lente.getMaterial(),
                MarcaResponseDTO.valueOf(lente.getMarca()),
                lente.getTratamento(),
                lente.getEspessura(),
                lente.getReceita()
        );
    }
}
