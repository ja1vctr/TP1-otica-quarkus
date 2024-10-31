package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record ArmacaoDTO(
        String nome,
        String material,
//        @NotBlank(message = "Este campo não pode ser null")
        String formato,
//        @NotBlank(message = "Este campo não pode ser null")
        String modelo,
//        @NotBlank(message = "Este campo não pode ser null")
        Integer idCategoria,
        Long medida,
        @NotBlank(message = "Este campo não pode ser null")
        Long cor,
        @NotBlank(message = "Este campo não pode ser null")
        Long marca
) {
}
