package br.unitins.topicos1.model.converterJpa;

import br.unitins.topicos1.model.pedido.Pagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PagamentoConverterJpa implements AttributeConverter<Pagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Pagamento pagamento) {
        return pagamento == null? null: pagamento.getId();
    }

    @Override
    public Pagamento convertToEntityAttribute(Integer id) {
        return Pagamento.valueOf(id);
    }
}
