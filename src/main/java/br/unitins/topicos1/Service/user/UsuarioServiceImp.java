package br.unitins.topicos1.Service.user;

import java.util.List;

import br.unitins.topicos1.Service.hash.HashServiceImp;
import br.unitins.topicos1.dto.cadastro.AlterCadastroDTO;
import br.unitins.topicos1.dto.cadastro.CadastroDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.user.Perfil;
import br.unitins.topicos1.model.user.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImp implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashServiceImp hashService;

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }
    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findByUsernameAndSenha(username, senha));
    }
    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return null;
    }
    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                                .stream()
                                .map(UsuarioResponseDTO::valueOf)  
                                .toList();
    }
    
    @Override
    @Transactional
    public UsuarioResponseDTO create(CadastroDTO dto) {
        Usuario usuario = new Usuario();
        
        usuario.setNome(dto.nome());
        usuario.setUsername(dto.username());
        String hash = hashService.getHashSenha(dto.senha());
        usuario.setSenha(hash);
        usuario.setPerfil(Perfil.USER);
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void alter(Long id, AlterCadastroDTO dto) {
        validarId(id);

        Usuario usuario = usuarioRepository.findById(id);

        usuario.setNome(dto.nome());
        usuario.setUsername(dto.username());
        String hash = hashService.getHashSenha(dto.senha());
        usuario.setSenha(hash);
        usuario.setPerfil(Perfil.valueOf(dto.perfil()));
        usuarioRepository.persist(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    ///////////VALIDATION/////////////
    
    public void validarId (Long id){
        if(usuarioRepository.findById(id) == null){
            throw new ValidationException("Usuario", "Objeto nao encontrado");
        }
    }
}