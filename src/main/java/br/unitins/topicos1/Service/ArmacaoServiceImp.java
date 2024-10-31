package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.*;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.MedidaRepository;
import br.unitins.topicos1.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ArmacaoServiceImp implements ArmacaoService{
    @Inject
    ArmacaoRepository armacaoRepository;
    @Inject
    CorRepository corRepository;
    @Inject
    MarcaRepository marcaRepository;
    @Inject
    MedidaRepository medidaRepository;

    @Override
    @Transactional
    public ArmacaoResponseDTO create(ArmacaoDTO dto) {
        Cor cor = validarCor(dto.cor(), dto);
        Marca marca = validarMarca(dto.marca(), dto);
        Medida medida = validarMedida(dto.medida(), dto);

        validarNomeArmacao(dto.nome());

        Armacao newArmacao = new Armacao();
        newArmacao.setId(null);
        newArmacao.setCor(cor);
        newArmacao.setNome(dto.nome());
        newArmacao.setCategoria(Categoria.valueOf(dto.idCategoria()));
        newArmacao.setModelo(dto.modelo());
        newArmacao.setMedida(medida);
        newArmacao.setMaterial(dto.material());
        newArmacao.setMarca(marca);
        newArmacao.setFormato(dto.formato());

        armacaoRepository.persist(newArmacao);
        return ArmacaoResponseDTO.valueOf(newArmacao);
    }

    @Override
    @Transactional
    public void alter(Long id, ArmacaoDTO dto) {
        validarIdArmacao(id);
        Cor cor = validarCor(dto.cor(), dto);
        Marca marca = validarMarca(dto.marca(), dto);
        Medida medida = validarMedida(dto.medida(), dto);

        Armacao alterArmacao = armacaoRepository.findById(id);

        alterArmacao.setNome(dto.nome());
        alterArmacao.setCategoria(Categoria.valueOf(dto.idCategoria()));
        alterArmacao.setMaterial(dto.material());
        alterArmacao.setFormato(dto.formato());
        alterArmacao.setMedida(medida);
        alterArmacao.setModelo(dto.modelo());
        alterArmacao.setCor(cor);
        alterArmacao.setMarca(marca);

        armacaoRepository.persist(alterArmacao);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdArmacao(id);
        armacaoRepository.deleteById(id);
    }

    @Override
    public List<Armacao> findAll(int page, int pageSize) {
        return armacaoRepository.findAll().page(Page.of(page,pageSize)).list();
    }

    @Override
    public Armacao findById(Long id) {
        return armacaoRepository.findById(id);
    }

    @Override
    public Armacao findByNome(String nome) {
        return armacaoRepository.findByNome(nome);
    }


    @Override
    public List<ArmacaoResponseDTO> findByListNome(String nome, int page, int pagesize) {
        return armacaoRepository.findByListNome(nome, page, pagesize)
                .stream()
                .map(ArmacaoResponseDTO::valueOf)
                .toList();
    }


    @Override
    public List<ArmacaoResponseDTO> findByListMedida(Integer medida, int page, int pageSize) {
        return armacaoRepository.findByListMedida(medida, page, pageSize)
                .stream()
                .map(ArmacaoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByListFormato(String formato, int page, int pagesize) {
        return armacaoRepository.findByListFormato(formato, page, pagesize)
                .page(Page.of(page, pagesize))
                .stream()
                .map(ArmacaoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> findByListModelo(String modelo, int page, int pagesize) {
        return armacaoRepository.findByListModelo(modelo, page, pagesize)
                .page(Page.of(page, pagesize))
                .stream()
                .map(ArmacaoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ArmacaoResponseDTO> dinamicSearch(String medida, String formato, String modelo, Double preco, String cor, String marca, int page, int pageSize) {
        return armacaoRepository.dinamicSearch(medida, formato, modelo, preco, cor, marca, page, pageSize)
                .stream()
                .map(ArmacaoResponseDTO::valueOf)
                .toList();
    }


    ///////////////// VALIDACOES /////////////////


    public void validarNomeArmacao(String nome) {
        if (armacaoRepository.findByNome(nome) != null) {
            throw new ValidationException("Armacao", "nome" + nome + "já existe");
        }
    }

    public void validarIdArmacao(Long id) {
        if (armacaoRepository.findById(id) == null) {
            throw new ValidationException("Armacao", "Objeto nao encontrado");
        }
    }

    public Cor validarCor(Long idCor, ArmacaoDTO dto) {
        Cor cor = corRepository.findById(dto.cor());

        if(cor == null){
            throw new IllegalArgumentException("Cor com id " + dto.cor() + " não encontrada.");
        }
        return cor;
    }

    public Marca validarMarca(Long idMarca, ArmacaoDTO dto) {
        Marca marca = marcaRepository.findById(dto.marca());

        if(marca == null){
            throw new IllegalArgumentException("Marca com id " + dto.marca() + " não encontrada.");
        }
        return marca;
    }

    public Medida validarMedida(Long idMedida, ArmacaoDTO dto) {
        Medida medida = medidaRepository.findById(dto.medida());

        if(medida == null){
            throw new IllegalArgumentException("Medida com id " + dto.marca() + " não encontrada.");
        }
        return medida;
    }
}
