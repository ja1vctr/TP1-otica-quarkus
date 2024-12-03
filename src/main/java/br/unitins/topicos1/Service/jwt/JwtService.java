package br.unitins.topicos1.Service.jwt;

import br.unitins.topicos1.dto.user.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}