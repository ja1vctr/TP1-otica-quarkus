package br.unitins.topicos1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
public abstract class Produto{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;
    @NotBlank(message = "Este campo não pode ser null")
    private Double preco;
    @NotBlank(message = "Este campo não pode ser null")
    private String nome;
    @NotBlank(message = "Este campo não pode ser null")
    private Status status;
}
