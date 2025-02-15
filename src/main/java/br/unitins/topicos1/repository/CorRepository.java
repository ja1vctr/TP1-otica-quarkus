package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    public Cor findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase()+ "%").firstResult();
    }

    public List<Cor> findByListNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase()+ "%").list();
    }
}
