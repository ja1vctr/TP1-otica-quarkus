package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record ArmacaoDTO(
        Double  preco,
        String  nome,
        Long    status,
        Integer quantidade,
        String  tamnho,
        String  tipo,
        String  material,
        String  formato,
        Long    Categoria,
        String  curvaDaLente
) {
}
