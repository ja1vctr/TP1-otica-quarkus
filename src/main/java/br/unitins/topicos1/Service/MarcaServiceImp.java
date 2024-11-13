package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImp implements MarcaService {

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public MarcaResponseDTO create(MarcaDTO dto) {
        validarNomeMarca(dto);
        Marca newMarca = new Marca();
        newMarca.setNome(dto.nome());

        marcaRepository.persist(newMarca);
        return MarcaResponseDTO.valueOf(newMarca);
    }

    @Override
    @Transactional
    public void alter(Long id, MarcaDTO dto) {
        validarIdMarca(id);
        marcaRepository.findById(id).setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdMarca(id);
        marcaRepository.deleteById(id);
    }

    @Override
    public List<MarcaResponseDTO> findAll() {
        return marcaRepository
                .findAll()
                .stream()
                .map(MarcaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public Marca findById(Long id) {
        return marcaRepository.findById(id);
    }

    @Override
    public List<MarcaResponseDTO> findByListNome(String nome) {
        if (nome == null) {
            throw new ValidationException("Marca", "nome nao pode ser Null");
        }

        return marcaRepository.findByListNome(nome)
                .stream()
                .map(MarcaResponseDTO::valueOf)
                .toList();
    }

    /////////// VALIDATION ///////////

    public void validarNomeMarca(MarcaDTO dto) {
        Marca marca = marcaRepository.findByNome(dto.nome());
        if (marca != null) {
            throw new ValidationException("Marca", "Objeto já existe");
        }
    }

    public void validarIdMarca(Long id) {
        if (marcaRepository.findById(id) == null) {
            throw new ValidationException("Marca", "Objeto nao encontrado");
        }
    }
}
