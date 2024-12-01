package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.OculosDeSol;
import br.unitins.topicos1.model.Status;

public record OculosDeSolResponseDTO(
        Long id,
        Double preco,
        String nome,
        Status status,
        Integer quantidade,
        String tamanho,
        String tipo,
        String material,
        MarcaResponseDTO marca,
        String modelo,
        Categoria categoria,
        String filtroSolar,
        CorResponseDTO cor
) {
    public static OculosDeSolResponseDTO valueOf(OculosDeSol oculos ){
        return new OculosDeSolResponseDTO(
                oculos.getId(),
                oculos.getPreco(),
                oculos.getNome(),
                oculos.getStatus(),
                oculos.getQuantidade(),
                oculos.getTamanho(),
                oculos.getTipo(),
                oculos.getMaterial(),
                MarcaResponseDTO.valueOf(oculos.getMarca()),
                oculos.getModelo(),
                oculos.getCategoria(),
                oculos.getFiltroSolar(),
                CorResponseDTO.valueOf(oculos.getCor())
        );
    }
}
