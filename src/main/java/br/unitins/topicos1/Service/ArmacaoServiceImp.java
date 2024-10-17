package br.unitins.topicos1.Service;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

public class ArmacaoServiceImp implements ArmacaoService{
    @Inject
    ArmacaoRepository armacaoRepository;
    @Inject
    CorRepository corRepository;
    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public ArmacaoResponseDTO create(ArmacaoDTO dto) {
        Cor cor = validarCor(dto.cor(), dto);
        Marca marca = validarMarca(dto.marca(), dto);
        validarNomeArmacao(dto.nome());

        Armacao newArmacao = new Armacao();
        newArmacao.setCor(cor);
        newArmacao.setPreco(dto.preco());
        newArmacao.setNome(dto.nome());
        newArmacao.setStatus(Status.valueOf(dto.status()));
        newArmacao.setModelo(dto.modelo());
        newArmacao.setMaterial(dto.material());
        newArmacao.setMarca(marca);
        newArmacao.setTamanho(dto.tamanho());
        newArmacao.setFormato(dto.formato());
        newArmacao.setCurvaLente(dto.curvaLente());

        return ArmacaoResponseDTO.valueOf(newArmacao);
    }

    @Override
    @Transactional
    public void alter(Long id, ArmacaoDTO dto) {
        validarIdArmacao(id);
        Cor cor = validarCor(dto.cor(), dto);
        Marca marca = validarMarca(dto.marca(), dto);

        Armacao alterArmacao = armacaoRepository.findById(id);

        alterArmacao.setPreco(dto.preco());
        alterArmacao.setNome(dto.nome());
        alterArmacao.setStatus(Status.valueOf(dto.status()));
        alterArmacao.setMaterial(dto.material());
        alterArmacao.setTamanho(dto.tamanho());
        alterArmacao.setFormato(dto.formato());
        alterArmacao.setCurvaLente(dto.curvaLente());
        alterArmacao.setModelo(dto.modelo());
        alterArmacao.setCor(cor);
        alterArmacao.setMarca(marca);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarIdArmacao(id);
        armacaoRepository.deleteById(id);
    }

    @Override
    public List<Marca> findAll(int page, int pageSize) {
        return null;
    }

    @Override
    public Marca findById(Long id) {
        return null;
    }

    @Override
    public Marca findByNome(String nome) {
        return null;
    }

    @Override
    public List<ArmacaoResponseDTO> findByListNome(String nome, int page, int pagesize) {
        return null;
    }

    @Override
    public List<ArmacaoResponseDTO> findByListTamanho(String tamanho, int page, int pagesize) {
        return null;
    }

    @Override
    public List<ArmacaoResponseDTO> findByListFormato(String formato, int page, int pagesize) {
        return null;
    }

    @Override
    public List<ArmacaoResponseDTO> findByListModelo(String modelo, int page, int pagesize) {
        return null;
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
            throw new IllegalArgumentException("Cor com id " + dto.marca() + " não encontrada.");
        }

        return marca;
    }
}
