package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Status;

public record ArmacaoResponseDTO(
        Long id,
        Double preco,
        String nome,
        Status status,
        Integer quantidade,
        String tamanho,
        String tipo,
        String material,
        MarcaResponseDTO marca,
        String formato,
        Categoria categoria,
        String curvaDaLente,
        CorResponseDTO cor,
        String nomeImagem
) {
    public static ArmacaoResponseDTO valueOf(Armacao armacao){
        return new ArmacaoResponseDTO(
                armacao.getId(),
                armacao.getPreco(),
                armacao.getNome(),
                armacao.getStatus(),
                armacao.getQuantidade(),
                armacao.getTamanho(),
                armacao.getTipo(),
                armacao.getMaterial(),
                MarcaResponseDTO.valueOf(armacao.getMarca()),
                armacao.getFormato(),
                armacao.getCategoria(),
                armacao.getCurvaDaLente(),
                CorResponseDTO.valueOf(armacao.getCor()),
                armacao.getNomeImagem()
        );
    }
}
