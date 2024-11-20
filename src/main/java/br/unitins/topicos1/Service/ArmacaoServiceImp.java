package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto) {
        Armacao armacao = new Armacao();

        armacao.setPreco(dto.preco());
        armacao.setNome(dto.nome());
        armacao.setStatus(Status.valueOf(dto.status()));
        armacao.setQuantidade(dto.quantidade());
        armacao.setTamanho(dto.tamnho());
        armacao.setTipo(dto.tipo());
        armacao.setMaterial(dto.material());
        armacao.setMarca(marcaRepository.findById(dto.marca()));
        armacao.setFormato(dto.formato());
        armacao.setCategoria(Categoria.valueOf(dto.categoria()));
        armacao.setCurvaDaLente(dto.curvaDaLente());
        armacao.setCor(corRepository.findById(dto.cor()));
        
        armacaoRepository.persist(armacao);

        return ArmacaoResponseDTO.valueOf(armacao);
        
    }

    @Override
    @Transactional
    public void alter(Long id, ArmacaoDTO dto) {
        Armacao armacao = armacaoRepository.findById(id);

        armacao.setPreco(dto.preco());
        armacao.setNome(dto.nome());
        armacao.setStatus(Status.valueOf(dto.status()));
        armacao.setQuantidade(dto.quantidade());
        armacao.setTamanho(dto.tamnho());
        armacao.setTipo(dto.tipo());
        armacao.setMaterial(dto.material());
        armacao.setMarca(marcaRepository.findById(dto.marca()));
        armacao.setFormato(dto.formato());
        armacao.setCategoria(Categoria.valueOf(dto.categoria()));
        armacao.setCurvaDaLente(dto.curvaDaLente());
        armacao.setCor(corRepository.findById(dto.cor()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        armacaoRepository.deleteById(id);
    }

    @Override
    public List<ArmacaoResponseDTO> findAll() {
        return armacaoRepository.findAll()
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public ArmacaoResponseDTO findById(Long id) {
        return ArmacaoResponseDTO.valueOf(armacaoRepository.findById(id));
        
    }

    @Override
    public List<ArmacaoResponseDTO> findByPreco(Double preco) {
        return armacaoRepository.findByPreco(preco)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByNome(String nome) {
        return armacaoRepository.findByListNome(nome)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByStatus(Integer idStatus) {
        Status status = Status.valueOf(idStatus);
        
            return armacaoRepository.findByListSatus(status)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByQuantidade(Integer quantidade) {
       return armacaoRepository.findByListQuantidade(quantidade)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByTamanho(String tamanho) {
        return armacaoRepository.findByListTamanho(tamanho)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByTipo(String tipo) {
        return armacaoRepository.findByListTipo(tipo)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByMaterial(String material) {
        return armacaoRepository.findByListMaterial(material)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByMarca(Long marca) {
            return armacaoRepository.findByListMarca(marca)
                                .stream()
                                .map(ArmacaoResponseDTO::valueOf)
                                .toList();
    }
    
}
