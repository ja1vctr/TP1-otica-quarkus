package br.unitins.topicos1.dto.cadastro;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterCadastroDTO (
    @NotBlank(message = "Campo nao pode ser nulo.")
    String nome,
    @NotBlank(message = "Campo nao pode ser nulo.")
    String username,
    @NotBlank(message = "Campo nao pode ser nulo.")
    @Size(min = 6)
    String senha,
    @NotNull()
    Integer perfil
){}
