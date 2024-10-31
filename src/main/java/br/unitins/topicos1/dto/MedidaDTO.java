package br.unitins.topicos1.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MedidaDTO(
        @NotBlank
        @Size(max = 7, message = "Tamanho incorreto. Adicione no maximo 7 caracteres")
        @Column(unique = true)
        String descricao
) {
}
