package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record MarcaDTO(
        @NotBlank(message = "Este campo não pode ser null")
        String nome
) {
}
