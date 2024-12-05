package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.model.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenteRepository implements PanacheRepository<Lente>{
    
    public List<Lente> findByPreco(Double preco) {
        return find("preco = ?1", preco).list();
    }

    public List<Lente> findByListNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase()+"%").list();
    }

    public List<Lente> findByListSatus(Status status) {
        return find("status = ?1", status).list();
    }

    public List<Lente> findByListQuantidade(int quantidade) {
        return find("quantidade = ?1", quantidade).list();
    }

    public List<Lente> findByListTamanho(String tamanho) {
        return find("UPPER(tamanho) = ?1", tamanho).list();
    }
    
    public List<Lente> findByListTipo(String tipo) {
        return find("UPPER(tipo) = ?1", tipo.toUpperCase()).list();
    }
    
    public List<Lente> findByListMaterial(String material) {
        return find("UPPER(material) LIKE ?1","%"+ material.toUpperCase()+ "%").list();
    }

    public List<Lente> findByListMarca(Long marca) {
        return find("marca.id = ?1", marca).list();
    }
    
}
