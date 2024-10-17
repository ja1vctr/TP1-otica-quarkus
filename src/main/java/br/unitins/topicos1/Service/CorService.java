package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CorService {

    public CorResponseDTO create(CorDTO dto);

    public void alter(long id, CorDTO dto);

    public void delete(Long id);

    public List<CorResponseDTO> findAll();

    public CorResponseDTO findById(Long id);

    public CorResponseDTO FindByNome(String nome);
}
