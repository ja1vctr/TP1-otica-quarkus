package br.unitins.topicos1.model.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.model.BaseEntity;
import br.unitins.topicos1.model.user.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido extends BaseEntity {
    private LocalDateTime data;
    private Double valorTotal; 
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> ListaItensPedido;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoEntrega;

    private StatusPedido statusPedido;

}
