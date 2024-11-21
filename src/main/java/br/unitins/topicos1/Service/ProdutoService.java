package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import jakarta.validation.Valid;

public interface ProdutoService<E,T> {
    public E create(@Valid T dto);
    public void alter(Long id, T dto);
    public void delete(Long id);
    public List<E> findAll();
    public E findById(Long id);
    public List<E> findByPreco(Double preco);
    public List<E> findByNome(String nome);
    public List<E> findByStatus(Integer status);
    public List<E> findByQuantidade(Integer quantidade);
    public List<E> findByTamanho(String tamanho);
    public List<E> findByTipo(String tipo);
    public List<E> findByMaterial(String material);
    public List<E> findByMarca(Long marca);
}
