package br.unitins.topicos1.Service.user;

import java.util.List;

import br.unitins.topicos1.dto.cadastro.AlterCadastroDTO;
import br.unitins.topicos1.dto.cadastro.CadastroDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO findById(Long id);
    UsuarioResponseDTO findByUsernameAndSenha(String username, String senha);
    List<UsuarioResponseDTO> findByNome(String nome);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO create(CadastroDTO dto);
    void alter(Long id, AlterCadastroDTO dto);
    void delete(Long id);
}

