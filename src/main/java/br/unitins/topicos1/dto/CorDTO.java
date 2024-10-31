package br.unitins.topicos1.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CorDTO(
        @NotBlank(message = "Este campo não pode ser null")
        String nome,
        @NotBlank(message = "Este campo não pode ser null")
        String indice) {
}
