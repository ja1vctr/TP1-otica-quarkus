package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Lente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LenteRespository implements PanacheRepository<Lente>{
    
    public List<Lente> findByTipoLente(String tipoLente){
        return find("tipoLente = ?1", tipoLente).list();
    }
    
    public List<Lente> findByCategoriaFiltroSolar(String categoriaFiltroSolar){
        return find("categoriaFiltroSolar = ?1", categoriaFiltroSolar).list();
    }
}
