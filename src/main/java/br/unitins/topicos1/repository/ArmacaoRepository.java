package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Armacao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoRepository implements PanacheRepository<Armacao> {
    public PanacheQuery<Armacao> findAll(){
        return find("from Armacao");
    }
    public Armacao findByNome (String nome){
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
    public PanacheQuery<Armacao> findByListNome (String nome){
        return find("UPPER(nome) LIKE ?1", "%"+nome.toUpperCase()+"%");
    }
    public PanacheQuery<Armacao> findByListTamanho (String tamanho){
        return find("UPPER(nome) LIKE ?1", "%"+tamanho.toUpperCase()+"%");
    }
    public PanacheQuery<Armacao> findByListFormato (String formato){
        return find("UPPER(nome) LIKE ?1", "%"+formato.toUpperCase()+"%");
    }
    public PanacheQuery<Armacao> findByListModelo (String modelo){
        return find("UPPER(nome) LIKE ?1", "%"+modelo.toUpperCase()+"%");
    }
    public PanacheQuery<Armacao> findByListPreco (Double preco){
        return find("UPPER(nome) = ?1", preco);
    }
}
