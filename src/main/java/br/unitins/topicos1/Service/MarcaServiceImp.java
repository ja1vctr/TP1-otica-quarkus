package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MarcaServiceImp implements MarcaService {

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public MarcaResponseDTO create(MarcaDTO dto) {
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
    public List<Marca> findAll(int page, int pageSize) {
        return marcaRepository.findAll().page(Page.of(page,pageSize)).list();
    }

    @Override
    public Marca findById(Long id) {
        return marcaRepository.findById(id);
    }

    @Override
    public Marca findByNome(String nome) {
        return marcaRepository.findByNome(nome);
    }

    @Override
    public List<Marca> findByListNome(String nome, int page, int pagesize) {
        return marcaRepository.findByListNome(nome).page(Page.of(page, pagesize)).list();
    }

    public void validarNomeMarca(String nomeMarca) {
        if (marcaRepository.findByNome(nomeMarca) != null) {
            throw new ValidationException("Marca", "Marca" + nomeMarca + "já existe");
        }
    }

    public void validarIdMarca(Long id) {
        if (marcaRepository.findById(id) == null) {
            throw new ValidationException("Marca", "Objeto nao encontrado");
        }
    }
}
