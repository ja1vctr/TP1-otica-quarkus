package br.unitins.topicos1.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ArmacaoDTO(
        @NotNull
        @PositiveOrZero
        Double  preco,
        @NotBlank(message = "Valor não pode ser nulo")
        String  nome,
        @NotNull
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
        Long    cor
) {
}
