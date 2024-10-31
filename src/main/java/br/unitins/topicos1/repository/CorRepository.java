package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    public PanacheQuery<Cor> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase()+ "%");
    }

    public Cor findByIndice(String indice){
        return find("UPPER(indice) = ?1", indice.toUpperCase()).firstResult();
    }
}
