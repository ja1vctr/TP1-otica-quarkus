package br.unitins.topicos1.Service;


import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import jakarta.validation.Valid;

public interface MarcaService {
    public MarcaResponseDTO create(@Valid MarcaDTO dto);

    public void alter(Long id, MarcaDTO dto);

    public void delete(Long id);

    public List<MarcaResponseDTO> findAll();

    public MarcaResponseDTO findById(Long id);

    public List<MarcaResponseDTO> findByListNome(String nome);

    public MarcaResponseDTO findByCnpj(String cnpj);
}
