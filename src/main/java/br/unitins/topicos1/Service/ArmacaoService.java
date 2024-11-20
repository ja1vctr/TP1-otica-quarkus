package br.unitins.topicos1.Service;

import java.io.ObjectInputFilter.Status;
import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import jakarta.validation.Valid;

public interface ArmacaoService {
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto);
    public void alter(Long id, ArmacaoDTO dto);
    public void delete(Long id);
    public List<ArmacaoResponseDTO> findAll();
    public ArmacaoResponseDTO findById(Long id);
    public List<ArmacaoResponseDTO> findByPreco(Double preco);
    public List<ArmacaoResponseDTO> findByNome(String nome);
    public List<ArmacaoResponseDTO> findByStatus(Integer status);
    public List<ArmacaoResponseDTO> findByQuantidade(Integer quantidade);
    public List<ArmacaoResponseDTO> findByTamanho(String tamanho);
    public List<ArmacaoResponseDTO> findByTipo(String tipo);
    public List<ArmacaoResponseDTO> findByMaterial(String material);
    public List<ArmacaoResponseDTO> findByMarca(Long marca);
}
