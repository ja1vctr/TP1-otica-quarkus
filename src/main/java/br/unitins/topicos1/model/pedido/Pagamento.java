package br.unitins.topicos1.model.pedido;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unitins.topicos1.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pagamento extends BaseEntity {
    private TipoPagamento tipoPagamento;
    private Double valor;
    private Boolean statusPagamento;
    private LocalDateTime dataConfirmacaoPagamento;
    @OneToOne
    @JoinColumn(name = "id_pedido", unique = true, nullable = false)
    @JsonIgnore
    private Pedido pedido;
}
