package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ArmacaoDTO(
        @NotNull
        @PositiveOrZero
        Double  preco,
        @NotBlank(message = "Valor não pode ser nulo")
        String  nome,
        @NotNull(message = "O status não pode ser nulo")
        Integer status,
        @PositiveOrZero
        Integer quantidade,
        String  tamnho,
        String  tipo,
        String  material,
        Long    marca,
        String  formato,
        Integer categoria,
        String  curvaDaLente,
        @NotNull(message = "O id da cor não pode ser nulo")
        Long    cor
) {}
