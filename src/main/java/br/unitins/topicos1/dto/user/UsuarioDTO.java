package br.unitins.topicos1.dto.user;

public record UsuarioDTO (
    String nome,
    String userName,
    String senha,
    Integer perfil
) {}
