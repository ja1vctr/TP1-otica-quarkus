package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ArmacaoDTO(
        @NotBlank(message = "Valor não pode ser nulo")
        @PositiveOrZero
        Double  preco,
        @NotBlank(message = "Valor não pode ser nulo")
        String  nome,
        @NotBlank(message = "Valor não pode ser nulo")
        Integer status,
        @NotBlank(message = "Valor não pode ser nulo")
        @PositiveOrZero
        Integer quantidade,
        String  tamnho,
        String  tipo,
        String  material,
        Long    marca,
        String  formato,
        @NotBlank(message = "Valor não pode ser nulo")
        Integer categoria,
        String  curvaDaLente,
        Long    cor
) {
}
