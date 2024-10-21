package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Armacao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ArmacaoRepository implements PanacheRepository<Armacao> {
    public PanacheQuery<Armacao> findAll() {
        return find("from Armacao");
    }

    public Armacao findByNome(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public PanacheQuery<Armacao> findByListNome(String nome, int page, int pageSize) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").page(Page.of(page, pageSize));
    }
    public PanacheQuery<Armacao> findByListPreco(Double preco, int page, int pageSize) {
        return find("UPPER(nome) = ?1", preco).page(Page.of(page, pageSize));
    }

    public PanacheQuery<Armacao> findByListStatus(String status, int page, int pageSize) {
        return find("UPPER(nome) = ?1", status).page(Page.of(page, pageSize));
    }

    public PanacheQuery<Armacao> findByListTamanho(String tamanho) {
        return find("UPPER(nome) LIKE ?1", "%" + tamanho.toUpperCase() + "%");
    }

    public PanacheQuery<Armacao> findByListFormato(String formato) {
        return find("UPPER(nome) LIKE ?1", "%" + formato.toUpperCase() + "%");
    }

    public PanacheQuery<Armacao> findByListModelo(String modelo) {
        return find("UPPER(nome) LIKE ?1", "%" + modelo.toUpperCase() + "%");
    }



    public PanacheQuery<Armacao> dinamicSearch(String tamanho, String formato, String modelo, Double preco, String cor, String marca, int page, int pageSize) {
        StringBuilder query = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (tamanho != null && !formato.isEmpty()) {
            query.append(" AND tamanho = :tamanho");
            params.put("tamanho", tamanho);
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