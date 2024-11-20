package br.unitins.topicos1.dto;

public record ArmacaoDTO(
        Double  preco,
        String  nome,
        Integer status,
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
