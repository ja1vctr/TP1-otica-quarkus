package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.pedido.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento>{
    public Pagamento findByPedidoId(Long idPedido) {
        return find("pedido.id", idPedido).firstResult();
    }
}
