package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record ArmacaoDTO(
        @NotBlank(message = "Este campo não pode ser null")
        Double preco,
        @NotBlank(message = "Este campo não pode ser null")
        String nome,
        @NotBlank(message = "Este campo não pode ser null")
        Integer status,
        String material,
        @NotBlank(message = "Este campo não pode ser null")
        String tamanho,
        @NotBlank(message = "Este campo não pode ser null")
        String formato,
        String curvaLente,
        @NotBlank(message = "Este campo não pode ser null")
        String modelo,
        @NotBlank(message = "Este campo não pode ser null")
        Long cor,
        @NotBlank(message = "Este campo não pode ser null")
        Long marca
) {
}
