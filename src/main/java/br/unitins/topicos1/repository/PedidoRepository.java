package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.pedido.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuarioId(Long usuarioId) {
        return find("usuario.id", usuarioId).list();
    }
}