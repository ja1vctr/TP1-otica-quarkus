package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    public PanacheQuery<Marca> findAll(){
        return find("from Marca");
    }

    public Marca findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").firstResult();
    }

    public PanacheQuery<Marca> findByListNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%" +nome.toUpperCase()+ "%");
    }

    public Marca findByCnpf(String cnpj){
        return find("UPPER(cnpj) = ?1", cnpj.toUpperCase()).firstResult();
    }

}
