package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.Armacao;
import jakarta.validation.Valid;

public interface ArmacaoService {
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto);
    public void alter(Long id, ArmacaoDTO dto);
    public void delete(Long id);
    public List<ArmacaoResponseDTO> findAll();
    public ArmacaoResponseDTO findById(Long id);
    public ArmacaoResponseDTO findByPreco(Double preco);
    public List<ArmacaoResponseDTO> findByNome(String nome);
    public List<ArmacaoResponseDTO> findByStatus(Long status);
    public List<ArmacaoResponseDTO> findByQuantidade(Integer quantidade);
    public List<ArmacaoResponseDTO> findByTamanho(String tamanho);
    public List<ArmacaoResponseDTO> findByTipo(String tipo);
    public List<ArmacaoResponseDTO> findByMaterial(String material);
    public List<ArmacaoResponseDTO> findByMarca(Long status);
}
