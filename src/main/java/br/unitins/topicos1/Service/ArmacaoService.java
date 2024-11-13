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
    public List<Armacao> findAll(int page, int pageSize);
    public Armacao findById(Long id);
    public Armacao findByNome(String nome);
    public List<ArmacaoResponseDTO> findByListNome(String nome, int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListMedida(Integer medida,  int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListFormato(String formato, int page, int pagesize);
    public List<ArmacaoResponseDTO> findByListModelo(String modelo, int page, int pagesize);
    public List<ArmacaoResponseDTO> dinamicSearch(String medida, String formato, String modelo, Double preco,String cor, String marca, int page, int pageSize);
}
