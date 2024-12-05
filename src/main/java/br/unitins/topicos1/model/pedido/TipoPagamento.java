package br.unitins.topicos1.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum TipoPagamento {
    BOLETO(1,"Boleto"),
    PIX(2,"Pix"),
    DEBITO(3,"Debito"),
    CREDITO(4,"Credito");

    int id;
    String nome;

    TipoPagamento(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }
}
