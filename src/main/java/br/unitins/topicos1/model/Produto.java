package br.unitins.topicos1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Produto{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_sequence", allocationSize = 1)
    private Long id;
    @NotBlank(message = "Este campo não pode ser null")
    private Double preco;
    @NotBlank(message = "Este campo não pode ser null")
    private String nome;
    @NotBlank(message = "Este campo não pode ser null")
    private Status status;
}
