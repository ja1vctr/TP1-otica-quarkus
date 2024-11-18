package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.repository.LenteRespository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LenteServicieImp implements LenteServicie {
    
    @Inject
    LenteRespository lenteRespository;

    @Override
    @Transactional
    public LenteResponseDTO create(LenteDTO dto){
        Lente lente = new Lente();

        lente.setTipoLente(dto.tipoLente());
        lente.setTratamento(dto.tratamento());
        lente.setCategoraiFiltroSolar(dto.categoriaFiltroSolar());
        lente.setAstgmatismo(dto.astgmatismo());
        lente.setMiopia(dto.miopia());
        lente.setHipermetropia(dto.hipermetropia());
        
        lenteRespository.persist(lente);

        return LenteResponseDTO.valueOf(lente);
    }

    @Override
    @Transactional
    public void alter(long id, LenteDTO dto){
        validarId(id);

        Lente lente = lenteRespository.findById(id);


        lente.setTipoLente(dto.tipoLente());
        lente.setTratamento(dto.tratamento());
        lente.setCategoraiFiltroSolar(dto.categoriaFiltroSolar());
        lente.setAstgmatismo(dto.astgmatismo());
        lente.setMiopia(dto.miopia());
        lente.setHipermetropia(dto.hipermetropia());
        
    }

    @Override
    @Transactional
    public void delete(Long id){
        validarId(id);
        lenteRespository.deleteById(id);
    }

    @Override
    public List<LenteResponseDTO> findAll(){
        return lenteRespository
                    .findAll()
                    .stream()
                    .map(LenteResponseDTO::valueOf)
                    .toList();
    }

    @Override
    public LenteResponseDTO findById(Long id){
        validarId(id);
        return LenteResponseDTO.valueOf(lenteRespository.findById(id));
    }

    @Override
    public List<LenteResponseDTO> findByTipoLente(String tipoLente){
        return lenteRespository
                    .findByTipoLente(tipoLente)
                    .stream()
                    .map(LenteResponseDTO::valueOf)
                    .toList();
    }
    
    @Override
    public List<LenteResponseDTO> findByCategoriaFiltroSolar(String categoriaFiltroSolar){
        return lenteRespository
                    .findByCategoriaFiltroSolar(categoriaFiltroSolar)
                    .stream()
                    .map(LenteResponseDTO::valueOf)
                    .toList();
    }

    ///////////////////
    
    public void validarId(Long id){
        if(lenteRespository.findById(id)==null){
            throw new ValidationException("Lente","Lente nao encontrada");
        }
    }
} 
