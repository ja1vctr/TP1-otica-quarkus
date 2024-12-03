package br.unitins.topicos1.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthDTO (
    @NotBlank(message = "O campo nao pode ser nulo.")
    String username,
    @NotBlank(message = "O campo nao pode ser nulo.")
    @Size(min = 6, message= "O campo deve ter tamanho mínimo de 6 caracteres")
    String senha
){}
