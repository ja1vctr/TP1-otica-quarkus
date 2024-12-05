package br.unitins.topicos1.model.converterJpa;

import br.unitins.topicos1.model.pedido.TipoPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPagamentoConverterJpa implements AttributeConverter<TipoPagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoPagamento pagamento) {
        return pagamento == null? null: pagamento.getId();
    }

    @Override
    public TipoPagamento convertToEntityAttribute(Integer id) {
        return TipoPagamento.valueOf(id);
    }
}
