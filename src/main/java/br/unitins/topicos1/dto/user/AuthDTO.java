package br.unitins.topicos1.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO (
    @NotBlank(message = "O campo nao pode ser nulo.")
    String userName,
    @NotBlank(message = "O campo nao pode ser nulo.")
    String senha
){}
