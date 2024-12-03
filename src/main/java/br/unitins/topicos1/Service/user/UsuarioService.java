package br.unitins.topicos1.Service.user;

import java.util.List;

import br.unitins.topicos1.dto.cadastro.CadastroDTO;
import br.unitins.topicos1.model.user.Usuario;

public interface UsuarioService {
    Usuario findById(Long id);
    Usuario findByUsernameAndSenha(String username, String senha);
    List<Usuario> findByNome(String nome);
    List<Usuario> findAll();
    Usuario create(CadastroDTO dto);
    Usuario update(Long id, CadastroDTO dto);
    void delete(Long id);
}

