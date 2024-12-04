package br.unitins.topicos1.dto.pedido;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO (
    @NotBlank(message = "O CEP não pode ser nulo")
    String cep,
    @NotBlank(message = "O estado não pode ser nulo")    
    String estado,
    @NotBlank(message = "A cidade não pode ser nula")
    String cidade,
    @NotBlank(message = "O bairro não pode ser nulo")
    String bairro,
    @NotBlank(message = "A rua não pode ser nula")
    String rua,
    @NotBlank(message = "O número não pode ser nulo")           
    String numero       
) {}
