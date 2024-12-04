package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record OculosDeSolDTO(
        @NotNull
        @PositiveOrZero(message = "valor tem que ser positivo")
        Double  preco,
        @NotBlank(message = "Valor não pode ser nulo")
        String  nome,
        @NotNull
        Integer status,
        @PositiveOrZero(message = "valor tem que ser positivo")
        Integer quantidade,
        String  tamnho,
        String  tipo,
        String  material,
        Long    marca,
        String  modelo,
        Integer categoria,
        String  filtroSolar,
        @NotNull(message = "O id da cor não pode ser nulo")
        Long    cor
) {
}
