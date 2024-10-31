package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.AcessorioDTO;
import br.unitins.topicos1.dto.AcessorioResponseDTO;
import br.unitins.topicos1.model.Acessorio;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.AcessorioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AcessorioServiceImp implements AcessorioService {
    @Inject
    AcessorioRepository acessorioRepository;

    @Override
    @Transactional
    public AcessorioResponseDTO create(AcessorioDTO dto) {
        if(acessorioRepository.findByNome(dto.nome()) != null)
            throw new ValidationException("Acessorio", "Este objeto ja existe!");
        Acessorio newAcessorio = new Acessorio();

        newAcessorio.setPreco(dto.preco());
        newAcessorio.setNome(dto.nome());
        newAcessorio.setStatus(Status.valueOf(dto.status()));
        newAcessorio.setQuantidade(dto.quantidade());
        newAcessorio.setDescricao(dto.descricao());

        acessorioRepository.persist(newAcessorio);

        return AcessorioResponseDTO.valueOf(newAcessorio);
    }

    @Override
    @Transactional
    public void alter(long id, AcessorioDTO dto) {
        Acessorio alterAcessorio = acessorioRepository.findById(id);

        if (alterAcessorio == null) {
            throw new ValidationException("Acessorio", "Objeto nao encontrado");
        }

        alterAcessorio.setPreco(dto.preco());
        alterAcessorio.setNome(dto.nome());
        alterAcessorio.setStatus(Status.valueOf(dto.status()));
        alterAcessorio.setQuantidade(dto.quantidade());
        alterAcessorio.setDescricao(dto.descricao());

        acessorioRepository.persist(alterAcessorio);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (acessorioRepository.findById(id) == null) {
            throw new ValidationException("Grau", "Objeto nao encontrado");
        }

        acessorioRepository.deleteById(id);
    }

    @Override
    public List<AcessorioResponseDTO> findAll() {
        return acessorioRepository
                .findAll()
                .stream()
                .map(AcessorioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public AcessorioResponseDTO findById(Long id) {
        return AcessorioResponseDTO.valueOf(acessorioRepository.findById(id));
    }

    @Override
    public AcessorioResponseDTO findByNome(String nome) {
        return AcessorioResponseDTO.valueOf(acessorioRepository.findByNome(nome));
    }

    @Override
    public List<AcessorioResponseDTO> findByStatus(Long status) {
        return acessorioRepository.findByStatus(status)
                .stream()
                .map(AcessorioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<AcessorioResponseDTO> findByDescricao(String descricao) {
        return acessorioRepository
                .findByDescricao(descricao)
                .stream()
                .map(AcessorioResponseDTO::valueOf)
                .toList();
    }

}
