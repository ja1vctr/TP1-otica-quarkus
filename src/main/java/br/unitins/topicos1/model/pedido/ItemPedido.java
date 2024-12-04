package br.unitins.topicos1.model.pedido;

import br.unitins.topicos1.model.BaseEntity;
import br.unitins.topicos1.model.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemPedido extends BaseEntity{
     private Integer quantidade;
     private Double precoUnitario;  
     @ManyToOne
     @JoinColumn(name = "id_produto") 
     private Produto produto;
}
