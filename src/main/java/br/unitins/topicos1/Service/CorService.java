package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;

public interface CorService {

    public CorResponseDTO create(CorDTO dto);

    public void alter(long id, CorDTO dto);

    public void delete(Long id);

    public List<CorResponseDTO> findAll();

    public CorResponseDTO findById(Long id);

    public List<CorResponseDTO> FindByNome(String nome);
}
