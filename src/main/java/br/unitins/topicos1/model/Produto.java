package br.unitins.topicos1.model;

import io.smallrye.common.constraint.NotNull;
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
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotNull
    @PositiveOrZero(message = "Este campo nao pode ser negativo")
    private Double preco;
    @NotBlank(message = "Este campo não pode ser null")
    private String nome;
    @NotNull
    @Column(name = "id_status")
    private Status status;
    @NotNull
    private Integer quantidade;
    private String tamanho;
    private String tipo;
    private String material;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    private String nomeImagem;
}
