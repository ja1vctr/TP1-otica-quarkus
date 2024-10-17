package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cor extends BaseEntity{
    private String nome;
    private String indice;
}
