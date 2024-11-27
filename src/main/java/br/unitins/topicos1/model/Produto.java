package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produto{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Este campo não pode ser null")
    private Double preco;
    @NotBlank(message = "Este campo não pode ser null")
    private String nome;
    @NotBlank(message = "Este campo não pode ser null")
    @Column(name = "id_status")
    private Status status;
    @NotBlank(message = "Este campo não pode ser null")
    private Integer quantidade;
    private String tamanho;
    private String tipo;
    private String material;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
}
