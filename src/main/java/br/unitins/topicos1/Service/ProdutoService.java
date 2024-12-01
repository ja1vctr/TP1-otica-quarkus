package br.unitins.topicos1.Service;

import java.util.List;

import jakarta.validation.Valid;

public interface ProdutoService<E,T> {
    E create(@Valid T dto);
    void alter(@Valid Long id, T dto);
    void delete(@Valid Long id);
    List<E> findAll();
    E findById(Long id);
    List<E> findByPreco(Double preco);
    List<E> findByNome(String nome);
    List<E> findByStatus(Integer status);
    List<E> findByQuantidade(Integer quantidade);
    List<E> findByTamanho(String tamanho);
    List<E> findByTipo(String tipo);
    List<E> findByMaterial(String material);
    List<E> findByMarca(Long marca);
    E updateNomeImagem(Long id, String nomeImagem);
}
