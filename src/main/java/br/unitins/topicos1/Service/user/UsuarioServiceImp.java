package br.unitins.topicos1.Service.user;

import java.util.List;

import br.unitins.topicos1.dto.cadastro.CadastroDTO;
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

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }
    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }
    @Override
    public List<Usuario> findByNome(String nome) {
        return null;
    }
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }
    
    @Override
    @Transactional
    public Usuario create(CadastroDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    @Transactional
    public Usuario update(Long id, CadastroDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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

