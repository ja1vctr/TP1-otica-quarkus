package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.AcessorioDTO;
import br.unitins.topicos1.dto.AcessorioResponseDTO;

import java.util.List;

public interface AcessorioService {
    public AcessorioResponseDTO create(AcessorioDTO dto);
    public void alter(long id, AcessorioDTO dto);
    public void delete(Long id);
    public List<AcessorioResponseDTO> findAll();
    public AcessorioResponseDTO findById(Long id);
    public AcessorioResponseDTO findByNome(String nome);
    public List<AcessorioResponseDTO> findByStatus(Long status);
    public List<AcessorioResponseDTO> findByDescricao(String descricao);


}
