package br.unitins.topicos1.model.converterJpa;

import br.unitins.topicos1.model.Categoria;
import jakarta.persistence.AttributeConverter;

public class CategoriaConverterJpa implements AttributeConverter<Categoria, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Categoria categoria) {
        return categoria.getId();
    }

    @Override
    public Categoria convertToEntityAttribute(Integer id) {
        return Categoria.valueOf(id);
    }
}
