package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ArmacaoServiceImp implements ProdutoService<ArmacaoResponseDTO, ArmacaoDTO>{
    @Inject
    ArmacaoRepository armacaoRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    MarcaRepository marcaRepository;


    @Override
    @Transactional
    public ArmacaoResponseDTO create(@Valid ArmacaoDTO dto) {
        validarMarca(dto);

        validarCategoria(dto);

        validarStatus(dto);
        
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
    public void alter(@Valid Long id, ArmacaoDTO dto) {
        validaId(id);

        validarMarca(dto);

        validarCategoria(dto);

        validarStatus(dto);

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
    public void delete(@Valid Long id) {
        validaId(id);
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
        validaId(id);
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
    
    @Override
    @Transactional
    public ArmacaoResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateNomeImagem'");
    }
    
    /*---------- VALIDATION ----------*/
    
    
    public void validaId(Long id){
        Armacao armacao = armacaoRepository.findById(id);
        if(armacao == null)
            throw new ValidationException("id","Valor não encontrado");
    }

    public void validarStatus(ArmacaoDTO dto){
        if(dto.status() != 1 && dto.status() !=2){
            throw new ValidationException("status", "Campo invalido");
        }
    }

    public void validarCategoria(ArmacaoDTO dto){
        if(dto.categoria() != 1 && dto.categoria() !=2 && dto.categoria() !=3 && dto.categoria() !=4){
            throw new ValidationException("categoria", "Campo invalido");
        }
    }
    
    public void validarMarca(ArmacaoDTO dto){
        Marca marca = marcaRepository.findById(dto.marca());
        if(marca == null){
            throw new ValidationException("marca", "Campo invalido");
        }
    }
}
