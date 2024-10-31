package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Status;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ArmacaoRepository implements PanacheRepository<Armacao> {
    public Armacao findByNome(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public PanacheQuery<Armacao> findByListNome(String nome, int page, int pageSize) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").page(Page.of(page, pageSize));
    }

    public PanacheQuery<Armacao> findByListMedida(Integer medida, int page, int pageSize) {
        return find("medida.id", medida).page(Page.of(page, pageSize));
    }

    public PanacheQuery<Armacao> findByListFormato(String formato, int page, int pageSize) {
        return find("UPPER(formato) LIKE ?1", "%" + formato.toUpperCase() + "%").page(Page.of(page, pageSize));
    }

    public PanacheQuery<Armacao> findByListModelo(String modelo, int page, int pageSize) {
        return find("UPPER(modelo) LIKE ?1", "%" + modelo.toUpperCase() + "%").page(Page.of(page, pageSize));
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