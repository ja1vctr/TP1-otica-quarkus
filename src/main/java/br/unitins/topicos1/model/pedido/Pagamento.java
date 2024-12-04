package br.unitins.topicos1.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Pagamento {
    BOLETO(1,"Boleto"),
    PIX(2,"Pix"),
    DEBITO(3,"Debito"),
    CREDITO(4,"Credito");

    int id;
    String nome;

    Pagamento(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static Pagamento valueOf(Integer id) throws IllegalArgumentException{
        for(Pagamento pagamento : Pagamento.values()){
            if(id == pagamento.id)
                return pagamento;
        }
        throw new IllegalArgumentException("id pagamento invalido");
    }
}
