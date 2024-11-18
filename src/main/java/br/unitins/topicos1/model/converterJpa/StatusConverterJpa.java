package br.unitins.topicos1.model.converterJpa;

import br.unitins.topicos1.model.Status;
import jakarta.persistence.AttributeConverter;

public class StatusConverterJpa implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        return Status.valueOf(id);
    }
}
