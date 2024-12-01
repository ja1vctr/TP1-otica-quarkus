package br.unitins.topicos1.Service;

import java.util.List;

import br.unitins.topicos1.dto.OculosDeSolDTO;
import br.unitins.topicos1.dto.OculosDeSolResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.OculosDeSol;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.OculosDeSolRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class OculosDeSolServiceImp implements ProdutoService<OculosDeSolResponseDTO, OculosDeSolDTO>{
    @Inject
    OculosDeSolRepository oculosDeSolRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    MarcaRepository marcaRepository;


    @Override
    @Transactional
    public OculosDeSolResponseDTO create(@Valid OculosDeSolDTO dto) {
        validarMarca(dto);

        validarCategoria(dto);

        validarStatus(dto);
        
        OculosDeSol oculosDeSol = new OculosDeSol();

        oculosDeSol.setPreco(dto.preco());
        oculosDeSol.setNome(dto.nome());
        oculosDeSol.setStatus(Status.valueOf(dto.status()));
        oculosDeSol.setQuantidade(dto.quantidade());
        oculosDeSol.setTamanho(dto.tamnho());
        oculosDeSol.setTipo(dto.tipo());
        oculosDeSol.setMaterial(dto.material());
        oculosDeSol.setMarca(marcaRepository.findById(dto.marca()));
        oculosDeSol.setModelo(dto.modelo());
        oculosDeSol.setCategoria(Categoria.valueOf(dto.categoria()));
        oculosDeSol.setFiltroSolar(dto.filtroSolar());
        oculosDeSol.setCor(corRepository.findById(dto.cor()));
        
        oculosDeSolRepository.persist(oculosDeSol);

        return OculosDeSolResponseDTO.valueOf(oculosDeSol);
    }

    @Override
    @Transactional
    public void alter(@Valid Long id, OculosDeSolDTO dto) {
        validaId(id);

        validarMarca(dto);

        validarCategoria(dto);

        validarStatus(dto);

        OculosDeSol oculosDeSol = oculosDeSolRepository.findById(id);

        oculosDeSol.setPreco(dto.preco());
        oculosDeSol.setNome(dto.nome());
        oculosDeSol.setStatus(Status.valueOf(dto.status()));
        oculosDeSol.setQuantidade(dto.quantidade());
        oculosDeSol.setTamanho(dto.tamnho());
        oculosDeSol.setTipo(dto.tipo());
        oculosDeSol.setMaterial(dto.material());
        oculosDeSol.setMarca(marcaRepository.findById(dto.marca()));
        oculosDeSol.setModelo(dto.modelo());
        oculosDeSol.setCategoria(Categoria.valueOf(dto.categoria()));
        oculosDeSol.setFiltroSolar(dto.filtroSolar());
        oculosDeSol.setCor(corRepository.findById(dto.cor()));
    }

    @Override
    @Transactional
    public void delete (@Valid Long id) {
        validaId(id);
        oculosDeSolRepository.deleteById(id);
    }

    @Override
    public List<OculosDeSolResponseDTO> findAll() {
        return oculosDeSolRepository.findAll()
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public OculosDeSolResponseDTO findById(Long id) {
        validaId(id);
        return OculosDeSolResponseDTO.valueOf(oculosDeSolRepository.findById(id));
        
    }

    @Override
    public List<OculosDeSolResponseDTO> findByPreco(Double preco) {
        return oculosDeSolRepository.findByPreco(preco)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByNome(String nome) {
        return oculosDeSolRepository.findByListNome(nome)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByStatus(Integer idStatus) {
        Status status = Status.valueOf(idStatus);
        
            return oculosDeSolRepository.findByListSatus(status)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByQuantidade(Integer quantidade) {
       return oculosDeSolRepository.findByListQuantidade(quantidade)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByTamanho(String tamanho) {
        return oculosDeSolRepository.findByListTamanho(tamanho)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByTipo(String tipo) {
        return oculosDeSolRepository.findByListTipo(tipo)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByMaterial(String material) {
        return oculosDeSolRepository.findByListMaterial(material)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }

    @Override
    public List<OculosDeSolResponseDTO> findByMarca(Long marca) {
            return oculosDeSolRepository.findByListMarca(marca)
                                .stream()
                                .map(OculosDeSolResponseDTO::valueOf)
                                .toList();
    }
    
    
    /*---------- VALIDATION ----------*/
    
    
    public void validaId(Long id){
        OculosDeSol oculosDeSol = oculosDeSolRepository.findById(id);
        if(oculosDeSol == null)
            throw new ValidationException("id","Valor não encontrado");
    }

    public void validarStatus(OculosDeSolDTO dto){
        if(dto.status() != 1 && dto.status() !=2){
            throw new ValidationException("status", "Campo invalido");
        }
    }

    public void validarCategoria(OculosDeSolDTO dto){
        if(dto.categoria() != 1 && dto.categoria() !=2 && dto.categoria() !=3 && dto.categoria() !=4){
            throw new ValidationException("categoria", "Campo invalido");
        }
    }
    
    public void validarMarca(OculosDeSolDTO dto){
        Marca marca = marcaRepository.findById(dto.marca());
        if(marca == null){
            throw new ValidationException("marca", "Campo invalido");
        }
    }
}
