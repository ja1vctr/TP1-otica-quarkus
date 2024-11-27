package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.LenteRespository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LenteServiceImp implements ProdutoService<LenteResponseDTO, LenteDTO> {
    
    @Inject
    LenteRespository lenteRepository;
    
    @Inject
    MarcaRepository  marcaRepository;
    
    
    @Override
    @Transactional
    public LenteResponseDTO create(LenteDTO dto) {
        Lente lente = new Lente();

        if(dto.status() != 1 && dto.status() !=2){
            throw new ValidationException("status", "Campo invalido");
        }

        Marca marca = marcaRepository.findById(dto.marca());
        if(marca == null){
            throw new ValidationException("marca", "Campo invalido");
        }
        
        lente.setPreco(dto.preco());
        lente.setNome(dto.nome());
        lente.setStatus(Status.valueOf(dto.status()));
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
    public void alter(Long id, LenteDTO dto) {
        validarId(id);
        Lente lente = lenteRepository.findById(id);

        if(dto.status() != 1 && dto.status() !=2){
            throw new ValidationException("status", "Campo invalido");
        }

        Marca marca = marcaRepository.findById(dto.marca());
        if(marca == null){
            throw new ValidationException("marca", "Campo invalido");
        }

        lente.setPreco(dto.preco());
        lente.setNome(dto.nome());
        lente.setStatus(Status.valueOf(dto.status()));
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
    public void delete(Long id) {
        validarId(id);
        lenteRepository.deleteById(id);
    }
    
    
    @Override
    public List findAll() {
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
    public List findByPreco(Double preco) {
        return lenteRepository.findByPreco(preco)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByNome(String nome) {
        return lenteRepository.findByListNome(nome)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    

    @Override
    public List findByStatus(Integer idStatus) {
        Status status = Status.valueOf(idStatus);

        return lenteRepository.findByListSatus(status)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByQuantidade(Integer quantidade) {
        return lenteRepository.findByListQuantidade(quantidade)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByTamanho(String tamanho) {
        return lenteRepository.findByListTamanho(tamanho)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByTipo(String tipo) {
        return lenteRepository.findByListTipo(tipo)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByMaterial(String material) {
        return lenteRepository.findByListMaterial(material)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    @Override
    public List findByMarca(Long marca) {
        return lenteRepository.findByListMarca(marca)
                                .stream()
                                .map(LenteResponseDTO::valueOf)
                                .toList();
    }
    
    
    ///////////////////
    
    public void validarId(Long id){
        if(lenteRepository.findById(id)==null){
            throw new ValidationException("Lente","Lente nao encontrada");
        }
    }
} 
