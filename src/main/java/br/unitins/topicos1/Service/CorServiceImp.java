package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class CorServiceImp implements CorService{
    @Inject
    CorRepository corRepository;

    @Override
    @Transactional
    public CorResponseDTO create(CorDTO dto) {
        Cor newCor = new Cor();
        newCor.setNome(dto.nome());

        corRepository.persist(newCor);

        return CorResponseDTO.valueOf(newCor);
    }

    @Override
    @Transactional
    public void alter(long id, CorDTO dto) {
        validarIdCor(id);

        Cor newCor = corRepository.findById(id);
        newCor.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdCor(id);
        corRepository.deleteById(id);
    }

    @Override
    public List<CorResponseDTO> findAll() {
        return corRepository
                .findAll()
                .stream()
                .map(CorResponseDTO::valueOf)
                .toList();
    }

    @Override
    public CorResponseDTO findById(Long id) {
        validarIdCor(id);
        return CorResponseDTO.valueOf(corRepository.findById(id));
    }

    @Override
    public List<CorResponseDTO> FindByNome(String nome) {
        if(corRepository.findByNome(nome) == null )
            throw new ValidationException("Cor", "Objeto não pode ser Null");
        return corRepository.findByNome(nome)
                .stream()
                .map(CorResponseDTO::valueOf)
                .toList();
    }


    ///////////////////////////////////////////////
    
    public void validarIdCor(Long id) {
        if (corRepository.findById(id) == null)
            throw new ValidationException("Cor", "Objeto nao encontrado");
    }
}
