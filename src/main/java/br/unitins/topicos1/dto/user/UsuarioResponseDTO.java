package br.unitins.topicos1.dto.user;

import br.unitins.topicos1.model.user.Perfil;
import br.unitins.topicos1.model.user.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String username,
    String senha,
    Perfil perfil
){
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getUsername(), 
            usuario.getSenha(), 
            usuario.getPerfil());
    }
}
