package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Medida;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedidaRepository implements PanacheRepository<Medida> {
    public PanacheQuery<Medida> findByDescricao(String descricao){
        return find("descricao LIKE ?1", "%" + descricao +"%");
    }
}
