package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.MedidaDTO;
import br.unitins.topicos1.dto.MedidaResponseDTO;

import java.util.List;

public interface MedidaService {
    public MedidaResponseDTO create(MedidaDTO dto);

    public void alter(long id, MedidaDTO dto);

    public void delete(Long id);

    public List<MedidaResponseDTO> findAll();

    public MedidaResponseDTO findById(Long id);
    public List<MedidaResponseDTO> findByDescricao(String descricao);
}
