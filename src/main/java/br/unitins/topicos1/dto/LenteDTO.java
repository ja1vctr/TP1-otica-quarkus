package br.unitins.topicos1.dto;

public record LenteDTO(
        String tipoLente,
        String tratamento,
        String categoriaFiltroSolar,
        Double astgmatismo,
        Double miopia,
        Double hipermetropia
) {
}
