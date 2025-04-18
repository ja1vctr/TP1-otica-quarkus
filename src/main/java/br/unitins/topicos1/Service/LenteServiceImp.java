package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.LenteRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class LenteServiceImp implements ProdutoService<LenteResponseDTO, LenteDTO> {
    
    @Inject
    LenteRepository lenteRepository;
    
    @Inject
    MarcaRepository  marcaRepository;
    
    
    @Override
    @Transactional
    public LenteResponseDTO create(@Valid LenteDTO dto) {
        validarStatus(dto);
        
        validarMarca(dto);

        Lente lente = new Lente();
        
        lente.setPreco(dto.preco());
        lente.setNome(dto.nome());
        
        if(dto.quantidade() == 0){
            lente.setStatus(Status.INDISPONIVEL);
        }else{
            lente.setStatus(Status.valueOf(dto.status()));
        }

        lente.setQuantidade(dto.quantidade());
        lente.setTamanho(dto.tamnho());
        lente.setTipo(dto.tipo());
        lente.setMaterial(dto.material());
        lente.setMarca(marcaRepository.findById(dto.marca()));
        lente.setTamanho(dto.tratamento());
        lente.setEspessura(dto.espessura());
        lente.setReceita(dto.receita());
        
        lenteRepository.persist(lente);

       return LenteResponseDTO.valueOf(lente);
    }
    
    @Override
    @Transactional
    public void alter(@Valid Long id, LenteDTO dto) {
        validarId(id);

        validarStatus(dto);

        validarMarca(dto);

        Lente lente = lenteRepository.findById(id);

        lente.setPreco(dto.preco());
        lente.setNome(dto.nome());
        
        if(dto.quantidade() == 0){
            lente.setStatus(Status.INDISPONIVEL);
        }else{
            lente.setStatus(Status.valueOf(dto.status()));
        }

        lente.setQuantidade(dto.quantidade());
        lente.setTamanho(dto.tamnho());
        lente.setTipo(dto.tipo());
        lente.setMaterial(dto.material());
        lente.setMarca(marcaRepository.findById(dto.marca()));
        lente.setTamanho(dto.tratamento());
        lente.setEspessura(dto.espessura());
        lente.setReceita(dto.receita());
    }

    
    @Override
    @Transactional
    public void delete(@Valid Long id) {
        validarId(id);
        lenteRepository.deleteById(id);
    }
    
    @Override
    public List<LenteResponseDTO>findAll() {
        return lenteRepository.findAll()
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public LenteResponseDTO findById(Long id) {
        validarId(id);
        return LenteResponseDTO.valueOf(lenteRepository.findById(id));
    }
    
    @Override
    public List<LenteResponseDTO>findByPreco(Double preco) {
        return lenteRepository.findByPreco(preco)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByNome(String nome) {
        return lenteRepository.findByListNome(nome)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByStatus(Integer idStatus) {
        Status status = Status.valueOf(idStatus);

        return lenteRepository.findByListSatus(status)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByQuantidade(Integer quantidade) {
        return lenteRepository.findByListQuantidade(quantidade)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByTamanho(String tamanho) {
        return lenteRepository.findByListTamanho(tamanho)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByTipo(String tipo) {
        return lenteRepository.findByListTipo(tipo)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByMaterial(String material) {
        return lenteRepository.findByListMaterial(material)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    @Override
    public List<LenteResponseDTO>findByMarca(Long marca) {
        return lenteRepository.findByListMarca(marca)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
                            
    @Override
    @Transactional
    public LenteResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        validarId(id);
        Lente lente = lenteRepository.findById(id);
        
        lente.setNomeImagem(nomeImagem);


        return LenteResponseDTO.valueOf(lente);
    }
    
    /*---------- VALIDATION ----------*/
    
    public void validarId(Long id){
        if(lenteRepository.findById(id)==null){
            throw new ValidationException("Lente","Lente nao encontrada");
        }
    }

    public void validarStatus(LenteDTO dto){
        if(dto.status() != 1 && dto.status() !=2){
            throw new ValidationException("status", "Campo invalido");
        }
    }
    
    public void validarMarca(LenteDTO dto){
        Marca marca = marcaRepository.findById(dto.marca());
        if(marca == null){
            throw new ValidationException("marca", "Campo invalido");
        }
    }

}
