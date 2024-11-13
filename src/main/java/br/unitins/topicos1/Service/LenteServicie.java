package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;

public interface LenteServicie {
    public LenteResponseDTO create(LenteDTO dto);

    public void alter(long id, LenteDTO dto);

    public void delete(Long id);

    public List<LenteResponseDTO> findAll();

    public LenteResponseDTO findById(Long id);

    public List<LenteResponseDTO> findByTipoLente(String tipoLente);
    
    public List<LenteResponseDTO> findByCategoriaFiltroSolar(String categoriaFiltroSolar);
} 
