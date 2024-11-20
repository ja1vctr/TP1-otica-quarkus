package br.unitins.topicos1.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Status;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoRepository implements PanacheRepository<Armacao> {
    
    public List<Armacao> findByPreco(Double preco) {
        return find("preco = ?1", preco).list();
    }

    public List<Armacao> findByListNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase()+"%").list();
    }

    public List<Armacao> findByListSatus(Status status) {
        return find("status = ?1", status).list();
    }

    public List<Armacao> findByListQuantidade(int quantidade) {
        return find("quantidade = ?1", quantidade).list();
    }

    public List<Armacao> findByListTamanho(String tamanho) {
        return find("UPPER(tamanho) = ?1", tamanho).list();
    }
    
    public List<Armacao> findByListTipo(String tipo) {
        return find("UPPER(tipo) = ?1", tipo).list();
    }
    
    public List<Armacao> findByListMaterial(String material) {
        return find("UPPER(material) = ?1", material).list();
    }

    public List<Armacao> findByListMarca(Long marca) {
        return find("marca.id = ?1", marca).list();
    }

    public List<Armacao> findByListFormato(String formato) {
        return find("UPPER(formato) = ?1", formato.toUpperCase()).list();
    }

    public List<Armacao> findByListCurvaLente(String curvaLente) {
        return find("UPPER(curvaLente) = ?1", curvaLente.toUpperCase()).list();
    }

    public List<Armacao> findByListCor(Long cor) {
        return find("cor = ?1", cor).list();
    }
    





    public PanacheQuery<Armacao> dinamicSearch(String medida, String formato, String modelo, Double preco, String cor, String marca, int page, int pageSize) {
        StringBuilder query = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (medida != null && !medida.isEmpty()) {
            query.append(" AND medida.descricao = :medida");
            params.put("medida", medida);
        }
        if (formato != null && !formato.isEmpty()) {
            query.append(" AND formato = :formato");
            params.put("formato", formato.toUpperCase());
        }
        if (modelo != null && !modelo.isEmpty()) {
            query.append(" AND modelo = :modelo");
            params.put("modelo", modelo.toUpperCase());
        }
        if (preco != null) {
            query.append(" AND preco <= :preco");
            params.put("preco", preco);
        }
        if (cor != null && cor.isEmpty()) {
            query.append(" AND cor.nome = :cor");
            params.put("cor", cor.toUpperCase());
        }
        if (marca != null && marca.isEmpty()) {
            query.append(" AND marca.nome = :marca");
            params.put("marca", marca.toUpperCase());
        }
        PanacheQuery<Armacao> paginatedQuery = find(query.toString(), params).page(Page.of(page, pageSize));
        return (PanacheQuery<Armacao>) paginatedQuery.list();
    }
}