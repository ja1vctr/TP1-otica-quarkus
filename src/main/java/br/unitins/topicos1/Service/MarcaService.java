package br.unitins.topicos1.Service;


import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.validation.Valid;

import java.util.List;

public interface MarcaService {
    public MarcaResponseDTO create(@Valid MarcaDTO dto);

    public void alter(Long id, MarcaDTO dto);

    public void delete(Long id);

    public List<MarcaResponseDTO> findAll();

    public Marca findById(Long id);

    public List<MarcaResponseDTO> findByListNome(String nome);
}
