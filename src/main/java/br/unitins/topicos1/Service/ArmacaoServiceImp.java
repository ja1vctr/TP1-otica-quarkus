package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ArmacaoServiceImp implements ArmacaoService{
    @Inject
    ArmacaoRepository armacaoRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    MarcaRepository marcaRepository;


    @Override
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void alter(Long id, ArmacaoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alter'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<ArmacaoResponseDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public ArmacaoResponseDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ArmacaoResponseDTO findByPreco(Double preco) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPreco'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByStatus(Long status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByStatus'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByQuantidade(Integer quantidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByQuantidade'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByTamanho(String tamanho) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTamanho'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByTipo(String tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTipo'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByMaterial(String material) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByMaterial'");
    }

    @Override
    public List<ArmacaoResponseDTO> findByMarca(Long status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByMarca'");
    }
    
}
