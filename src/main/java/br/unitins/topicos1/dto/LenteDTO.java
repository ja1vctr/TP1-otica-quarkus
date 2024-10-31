package br.unitins.topicos1.dto;

public record LenteDTO(
        String tipoLente,
        String material,
        Double indideRefracao,
        String tratamento,
        String categoriaFiltroSolar,
        Double astgmatismo,
        Double miopia,
        Double hipermetropia
) {
}
