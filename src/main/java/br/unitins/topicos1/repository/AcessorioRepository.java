package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Acessorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AcessorioRepository implements PanacheRepository<Acessorio> {
    public Acessorio findByNome(String nome) {
        return find("nome = ?1", nome).firstResult();
    }

    public List<Acessorio> findByStatus(Long status){
        return find("status = ?1", status).list();
    }

    public List<Acessorio> findByDescricao(String descricao) {
        return find("descricao LIKE ?1", "%" + descricao + "%").list();
    }
}
