package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.MedidaDTO;
import br.unitins.topicos1.dto.MedidaResponseDTO;
import br.unitins.topicos1.model.Medida;
import br.unitins.topicos1.repository.MedidaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MedidaServiceImp implements MedidaService{
    @Inject
    MedidaRepository medidaRepository;

    @Override
    @Transactional
    public MedidaResponseDTO create(MedidaDTO dto) {
        validarExisteDescricao(dto);

        Medida newMedida = new Medida();
        newMedida.setDescricao(dto.descricao());

        medidaRepository.persist(newMedida);
        return MedidaResponseDTO.valueOf(newMedida);
    }

    @Override
    @Transactional
    public void alter(long id, MedidaDTO dto) {
        validarIdMedida(id);

        Medida medida = medidaRepository.findById(id);
        medida.setDescricao(dto.descricao());;

        medidaRepository.persist(medida);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdMedida(id);

        medidaRepository.deleteById(id);
    }

    @Override
    public List<MedidaResponseDTO> findAll() {
        return medidaRepository
                .findAll()
                .stream()
                .map(MedidaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public MedidaResponseDTO findById(Long id) {
        return MedidaResponseDTO.valueOf(medidaRepository.findById(id));
    }

    @Override
    public List<MedidaResponseDTO> findByDescricao(String descricao) {
        return medidaRepository
                .findByDescricao(descricao)
                .stream()
                .map(MedidaResponseDTO::valueOf)
                .toList();
    }


    /////////////////////////////


    public void validarExisteDescricao(MedidaDTO dto){
        Medida medida = medidaRepository.findByDescricao(dto.descricao()).firstResult();

        if(medida != null){
            throw new ValidationException("Medida","medida: ["+ dto.descricao() + "] já existe");
        }
    }

    public void validarNullIndiceCorante(String descricao){
        if(medidaRepository.findByDescricao(descricao) == null)
            throw new ValidationException("Medida","medida nao existe");
    }

    public void validarIdMedida(Long id) {
        if (medidaRepository.findById(id) == null)
            throw new ValidationException("Medida", "Objeto nao encontrado");
    }
}
