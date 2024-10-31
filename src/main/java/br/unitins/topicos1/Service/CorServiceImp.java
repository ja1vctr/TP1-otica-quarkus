package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.validation.ValidationException;
import io.netty.channel.unix.Errors;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
@ApplicationScoped
public class CorServiceImp implements CorService{
    @Inject
    CorRepository corRepository;

    @Override
    @Transactional
    public CorResponseDTO create(CorDTO dto) {
        validarExisteIndiceCor(dto);

        Cor newCor = new Cor();
        newCor.setNome(dto.nome());
        newCor.setIndice(dto.indice());

        corRepository.persist(newCor);

        return CorResponseDTO.valueOf(newCor);
    }

    @Override
    @Transactional
    public void alter(long id, CorDTO dto) {
        validarIdCor(id);

        Cor newCor = corRepository.findById(id);
        newCor.setNome(dto.nome());
        newCor.setIndice(dto.indice());
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

    @Override
    public CorResponseDTO FindByindiceCorante(String indice) {
       validarNullIndiceCorante(indice);
        return CorResponseDTO.valueOf(corRepository.findByIndice(indice));
    }




    ///////////////////////////////////////////////

    public void validarExisteIndiceCor(CorDTO dto){
        Cor verificaCor = corRepository.findByIndice(dto.indice());

        if(verificaCor != null){
            throw new ValidationException("Cor","indice: ["+ dto.indice() + "] já existe");
        }
    }

    public void validarNullIndiceCorante(String indice){
        if(corRepository.findByIndice(indice) == null)
            throw new ValidationException("Indice","Indice Corante nao existe");
    }

    public void validarIdCor(Long id) {
        if (corRepository.findById(id) == null)
            throw new ValidationException("Cor", "Objeto nao encontrado");
    }
}
