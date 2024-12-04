package br.unitins.topicos1.model.pedido;

import br.unitins.topicos1.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco extends BaseEntity{
    public String cep;
    public String estado;
    public String cidade;
    public String bairro;
    public String rua;
    public String numero;
}
