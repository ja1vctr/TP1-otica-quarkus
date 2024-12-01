package br.unitins.topicos1.repository;


import java.util.List;

import br.unitins.topicos1.model.OculosDeSol;
import br.unitins.topicos1.model.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OculosDeSolRepository implements PanacheRepository<OculosDeSol> {
    
    public List<OculosDeSol> findByPreco(Double preco) {
        return find("preco = ?1", preco).list();
    }

    public List<OculosDeSol> findByListNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase()+"%").list();
    }

    public List<OculosDeSol> findByListSatus(Status status) {
        return find("status = ?1", status).list();
    }

    public List<OculosDeSol> findByListQuantidade(int quantidade) {
        return find("quantidade = ?1", quantidade).list();
    }

    public List<OculosDeSol> findByListTamanho(String tamanho) {
        return find("UPPER(tamanho) = ?1", tamanho).list();
    }
    
    public List<OculosDeSol> findByListTipo(String tipo) {
        return find("UPPER(tipo) = ?1", tipo.toUpperCase()).list();
    }
    
    public List<OculosDeSol> findByListMaterial(String material) {
        return find("UPPER(material) LIKE ?1","%"+ material.toUpperCase()+ "%").list();
    }

    public List<OculosDeSol> findByListMarca(Long marca) {
        return find("marca.id = ?1", marca).list();
    }

    public List<OculosDeSol> findByListModelo(String modelo) {
        return find("UPPER(modelo) LIKE ?1", "%" +  modelo.toUpperCase() + "%").list();
    }

    public List<OculosDeSol> findByListFiltroSolar(String filtroSolar) {
        return find("UPPER(filtroSolar) = ?1", filtroSolar.toUpperCase()).list();
    }

    public List<OculosDeSol> findByListCor(Long cor) {
        return find("cor = ?1", cor).list();
    }
}