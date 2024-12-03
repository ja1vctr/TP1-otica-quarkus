package br.unitins.topicos1.dto.cadastro;

import br.unitins.topicos1.model.user.Usuario;

public record CadastroResponseDTO (
    Long id,
    String nome,
    String username,
    String senha
){
    public CadastroResponseDTO valueOf(Usuario usuario){
        return new CadastroResponseDTO(
            usuario.getId(), 
            usuario.getNome(), 
            usuario.getUserName(), 
            usuario.getSenha());
    }
}
