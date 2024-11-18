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
        validarCnpjMarca(dto);
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setCnpj(dto.cnpj());

        marcaRepository.persist(marca);
        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public void alter(Long id, MarcaDTO dto) {
        validarIdMarca(id);
        Marca marca = marcaRepository.findById(id);
        marca.setNome(dto.nome());
        marca.setCnpj(dto.cnpj());

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
    public MarcaResponseDTO findById(Long id) {
        validarIdMarca(id);
        return MarcaResponseDTO.valueOf(marcaRepository.findById(id));
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

    @Override
    public MarcaResponseDTO findByCnpj(String cnpj) {
        try{
            return MarcaResponseDTO.valueOf(marcaRepository.findByCnpf(cnpj));
        }catch( Exception e){
            throw new ValidationException("cnpj", "cnpj nao encontrado");
        }
    }

    /////////// VALIDATION ///////////

    public void validarCnpjMarca(MarcaDTO dto) {
        Marca marca = marcaRepository.findByCnpf(dto.cnpj());
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
