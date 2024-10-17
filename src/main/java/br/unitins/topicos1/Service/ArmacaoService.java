package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.*;
import br.unitins.topicos1.model.Marca;
import jakarta.validation.Valid;

import java.util.List;

public interface ArmacaoService {
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto);
    public void alter(Long id, ArmacaoDTO dto);
    public void delete(Long id);
    public List<Marca> findAll(int page, int pageSize);
    public Marca findById(Long id);
    public Marca findByNome(String nome);
    public List<ArmacaoResponseDTO> findByListNome(String nome, int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListTamanho(String tamanho, int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListFormato(String formato, int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListModelo(String modelo, int page, int pagesize);
}
